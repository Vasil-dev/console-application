package ua.foxminded.vasilmartsyniuk.consoleapp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class Group {
    private int groupId;
    private String groupName;

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
}
