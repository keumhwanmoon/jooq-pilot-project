package project.pilot.api.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pilot.api.group.dao.GroupDAO;
import project.pilot.api.group.dto.GroupDTO;

import java.util.List;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-11
 */
@Service
public class GroupService {
    private final GroupDAO groupDAO;

    @Autowired
    public GroupService(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public List<GroupDTO> getList() {
        return groupDAO.getList();
    }
}
