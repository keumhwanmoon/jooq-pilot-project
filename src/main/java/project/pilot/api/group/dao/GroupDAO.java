package project.pilot.api.group.dao;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pilot.api.group.dto.GroupDTO;
import project.pilot.api.jooq.tables.Grp;

import java.util.List;

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

    public List<GroupDTO> getList() {
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
}