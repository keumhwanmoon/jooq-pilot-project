package project.pilot.api.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.pilot.api.group.dto.GroupDTO;
import project.pilot.api.group.service.GroupService;

import java.util.List;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-11
 */
@RestController
public class GroupRestController {
    private final GroupService groupService;

    @Autowired
    public GroupRestController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group")
    public List<GroupDTO> getList(@RequestParam(required = false) String type) {
        if ("R".equals(type)) { // Recursive
            return groupService.getRecursiveList();
        } else { // Hierarchical
            return groupService.getHierarchicalList();
        }
    }
}
