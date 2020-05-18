package com.yuntongxun.ytx.utils;


import com.yuntongxun.ytx.pojo.vo.sys.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象转树形结构工具类
 * @author tangxy
 * @date 2019-07-10
 */
public class TreeUtils {
    /**
     * 根节点值
     */
    private static final String GEN_NODE_ID = "0";

    protected TreeUtils(){

    }

    public static <T> Tree<T> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            Long pid = children.getParentId();
            if (pid == null || GEN_NODE_ID.equals(pid)) {
                topNodes.add(children);
                return;
            }
            for (Tree<T> parent : nodes) {
                Long id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setHasChildren(true);
                    return;
                }
            }
        });

        Tree<T> root = new Tree<>();
        root.setId(0L);
        root.setParentId(null);
        root.setHasParent(false);
        root.setHasChildren(true);
        root.setChecked(true);
        root.setChildren(topNodes);
        root.setText("根节点");
        Map<String, Object> state = new HashMap<>(16);
        state.put("opened", true);
        root.setState(state);
        return root;
    }

}
