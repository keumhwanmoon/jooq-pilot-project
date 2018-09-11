package project.pilot.api.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Integer seq;
    private Integer parSeq;
    private String grpName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<GroupDTO> children;
}
