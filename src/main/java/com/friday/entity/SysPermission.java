package com.friday.entity;

import lombok.Data;
import java.util.List;

@Data
public class SysPermission extends BaseEntity<Integer> {
    private static final long serialVersionUID = 5224848456484318100L;

    private Integer parentId;
    private String name;
    private String css;
    private String href;
    private Integer type;
    private String permission;
    private Integer sort;

    private List<SysPermission> child;

    @Override
    public String toString() {
        return "SysPermission{" +
                "parentId=" + parentId +
                ", name='" + name + '\'' +
                ", css='" + css + '\'' +
                ", href='" + href + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", sort=" + sort +
                ", child=" + child +
                '}';
    }
}
