/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.yuntongxun.ytx.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;


/**
 * Map工具类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * Map中根据key批量删除键值对
     * @param map
     * @param excludeKeys
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map removeEntries(Map<K,V> map, K[] excludeKeys){
        Iterator<K> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            K key = iterator.next();
            if(ArrayUtils.contains(excludeKeys,key)){
                iterator.remove();
                map.remove(key);
            }
        }
        return map;
    }

    /**
     * map 根据key排序
     * @param map
     * @param <V>
     * @return
     */
    public static <V> Map sortMapByKey(Map<String,V> map){
        if(null == map || map.isEmpty()){
            return null;
        }
        Map<String,V> sortMap = new TreeMap<String,V>(){
            class MayKeyComparator implements Comparator<String> {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            }
        };
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 转换map为str
     * @param map
     * @param <V>
     * @return
     */
    public static <V> String getMapStr(Map<String,V> map){
        Set<Entry<String, V>> entries = map.entrySet();
        Iterator<Entry<String, V>> iterator = entries.iterator();
        StringBuilder result = new StringBuilder();
        while(iterator.hasNext()){
            Entry<String, V> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue().toString();
            result.append(key).append(value);
        }
        return result.toString();
    }
}
