package project.pilot.api.group.dao;

import com.google.common.collect.Lists;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pilot.api.group.dto.GroupDTO;
import project.pilot.api.jooq.tables.Grp;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.jooq.impl.DSL.*;
import static project.pilot.api.jooq.tables.Grp.GRP;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-11
 */
@Repository
public class GroupDAO {
    private final DSLContext dslContext;

    @Autowired
    public GroupDAO(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<GroupDTO> getRecursiveList() {
        Grp T = GRP.as("T");

        return dslContext
                .withRecursive("ROOT", "SEQ", "PAR_SEQ", "GRP_NAME")
                .as(
                        select(T.SEQ.as("SEQ"), T.PAR_SEQ.as("PAR_SEQ"), T.GRP_NAME.as("GRP_NAME"))
                                .from(T)
                                .where(T.PAR_SEQ.isNull())
                ).with("CTE", "SEQ", "PAR_SEQ", "GRP_NAME")
                .as(
                        select(T.SEQ.as("SEQ"), T.PAR_SEQ.as("PAR_SEQ"), concat(field(name("ROOT", "GRP_NAME")), DSL.val("/"), T.GRP_NAME).as("GRP_NAME"))
                                .from(T)
                                .innerJoin(table(name("ROOT")))
                                .on(T.PAR_SEQ.eq(field(name("ROOT", "SEQ"), Integer.class)))
                                .where(T.PAR_SEQ.isNotNull())
                )
                .select(field(name("CTE", "SEQ")),
                        field(name("CTE", "PAR_SEQ")),
                        field(name("CTE", "GRP_NAME")))
                .from(table(name("CTE")))
                .fetch()
                .into(GroupDTO.class);
    }

    public List<GroupDTO> getHierarchicalList() {
        Grp T1 = GRP.as("T1");
        Grp T2 = GRP.as("T2");

        Map<Record, Result<Record>> recordResultMap = dslContext
                .select()
                .from(T1)
                .join(T2)
                .on(T1.SEQ.eq(T2.PAR_SEQ))
                .where(T1.PAR_SEQ.isNull())
                .fetch()
                .intoGroups(T1.fields());

        List<GroupDTO> resultList = Lists.newArrayList();

        recordResultMap.forEach((record, result) -> {
            GroupDTO group = record.into(GroupDTO.class);

            List<GroupDTO> children = Lists.newArrayList();

            result.forEach(r -> {
                if (Objects.nonNull(r.getValue(5))) {
                    children.add(r.into(GroupDTO.class));
                }
            });

            group.setChildren(children);

            resultList.add(group);
        });

        return resultList;

    }
}