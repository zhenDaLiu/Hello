package com.yuntongxun.ytx.pojo.vo.sys;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tree<T> {
    /**
     * 节点ID
     */
    private Long id;
    /**
     * 图标
     */
    private String icon;
    /**
     * url
     */
    private String url;
    /**
     * 显示节点文本
     */
    private String text;
    /**
     * 节点状态，open closed
     */
    private Map<String, Object> state;
    /**
     * 节点是否被选中 true false
     */
    private boolean checked = false;
    /**
     * 节点属性
     */
    private Map<String, Object> attributes;

    /**
     * 节点的子节点
     */
    private List<Tree<T>> children = new ArrayList<>();

    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 是否有父节点
     */
    private boolean hasParent = false;
    /**
     * 是否有子节点
     */
    private boolean hasChildren = false;




}
