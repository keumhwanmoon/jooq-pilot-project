package project.pilot.api.group.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pilot.api.group.dto.GroupDTO;

import java.util.List;

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
        return dslContext
                .select()
                .from(GRP)
                .fetch()
                .into(GroupDTO.class);
    }
}
