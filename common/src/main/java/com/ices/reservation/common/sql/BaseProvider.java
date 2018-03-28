package com.ices.reservation.common.sql;

import com.ices.reservation.common.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 20:53 2018/3/22 0022
 */
public class BaseProvider {
    private Logger log = LoggerFactory.getLogger(BaseProvider.class);
    public static String ClassName = "clz";


    protected String getTableName(Object t) {
        Class clz = t.getClass();
        if (clz.isAnnotationPresent(Table.class)) {
            Table table = (Table)t.getClass().getAnnotation(Table.class);
            return table.name();
        } else {
            return t.getClass().getName();
        }
    }

    private StringBuilder maptosb(Map<String, String> map) {
        int size = map.size();
        if (size == 0) {
            return new StringBuilder(" 1=1 ");
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator var4 = map.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var4.next();
                sb.append((String)entry.getKey()).append(" = ").append("'").append(String.valueOf(entry.getValue())).append("'");
                if (size > 1) {
                    sb.append(" and ");
                    --size;
                }
            }

            return sb;
        }
    }

    protected String getInsertCore(Object obj, int size) {
        Class clz = obj.getClass();
        Field[] fields = clz.getFields();
        StringBuilder classField = new StringBuilder(" ( ");
        StringBuilder tbColumn = new StringBuilder(" (");
        Field[] var7 = fields;
        int var8 = fields.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            Field field = var7[var9];

            try {
                if (field.isAnnotationPresent(Column.class) && field.get(obj) != null) {
                    Column fieldAnno = (Column)field.getAnnotation(Column.class);
                    if (StringUtils.isEmpty(fieldAnno.column())) {
                        tbColumn.append("`").append(field.getName()).append("`").append(",");
                    } else {
                        tbColumn.append("`").append(fieldAnno.column()).append("`").append(",");
                    }

                    if (size > 1) {
                        classField.append("#'{'").append("list[{0}]").append(".").append(field.getName()).append("'}'").append(",");
                    } else {
                        classField.append("#{").append(ClassName).append(".").append(field.getName()).append("}").append(",");
                    }
                }
            } catch (IllegalAccessException var12) {
                var12.printStackTrace();
            }
        }

        tbColumn.setCharAt(tbColumn.length() - 1, ')');
        StringBuilder sb = new StringBuilder("insert into ");
        sb.append(this.getTableName(obj));
        sb.append(tbColumn);
        sb.append(" values ");
        classField.setCharAt(classField.length() - 1, ')');
        if (size > 1) {
            MessageFormat messageFormat = new MessageFormat(classField.toString());
            StringBuilder temp = new StringBuilder();

            for(int i = 0; i < size; ++i) {
                temp.append(messageFormat.format(new Integer[]{i}));
                temp.append(",");
            }

            temp.deleteCharAt(temp.length() - 1);
            classField = temp;
        }

        sb.append(classField);
        return sb.toString();
    }

    protected String getUpdateCore(Object obj, String where) throws Exception {
        Class clz = obj.getClass();
        Field[] fields = clz.getFields();
        StringBuilder sb = new StringBuilder("update ");
        sb.append(this.getTableName(obj)).append(" set ");
        HashMap<String, String> map = new HashMap();
        Field[] var7 = fields;
        int var8 = fields.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            Field field = var7[var9];
            if (field.isAnnotationPresent(Column.class)) {
                Column fieldAnno = (Column)field.getAnnotation(Column.class);
                if (fieldAnno.isId() && field.get(obj) != null) {
                    if (StringUtils.isNotEmpty(fieldAnno.column())) {
                        map.put(fieldAnno.column(), String.valueOf(field.get(obj)));
                    } else {
                        map.put(field.getName(), String.valueOf(field.get(obj)));
                    }
                } else {
                    try {
                        if (fieldAnno.isUpdateSet() && field.get(obj) != null) {
                            if (StringUtils.isNotEmpty(fieldAnno.column())) {
                                sb.append(fieldAnno.column());
                            } else {
                                sb.append(field.getName());
                            }

                            sb.append(" = ").append("'").append(String.valueOf(field.get(obj))).append("'").append(",");
                        }
                    } catch (IllegalAccessException var13) {
                        this.log.error("获取sql出错");
                    }
                }
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        if (StringUtils.isEmpty(where)) {
            sb.append(" where ");
            sb.append(this.maptosb(map));
        } else {
            sb.append(where);
        }

        String sql = sb.toString();
        this.log.info("Update Sql: {}", sql);
        return sql;
    }

    protected String getDeleteCore(Object obj) {
        StringBuilder sb = new StringBuilder("delete from ");
        sb.append(this.getTableName(obj));
        sb.append(this.getWhereByObj(obj, false, false, (String)null));
        return sb.toString();
    }

    public String getSelectCore(Object obj, String where) throws Exception {
        Class clz = obj.getClass();
        Field[] fields = clz.getFields();
        StringBuilder sb = new StringBuilder("select ");
        Field[] var6 = fields;
        int var7 = fields.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Field field = var6[var8];
            if (field.isAnnotationPresent(Column.class)) {
                Column fieldAnno = (Column)field.getAnnotation(Column.class);
                if (fieldAnno.isSelectSet()) {
                    if (StringUtils.isEmpty(fieldAnno.column())) {
                        sb.append(field.getName()).append(",");
                    } else {
                        sb.append(fieldAnno.isDateTime() ? " DATE_FORMAT( " + fieldAnno.column() + " , '%Y-%m-%d %H:%i:%s')" : fieldAnno.column());
                        sb.append(" as ").append(field.getName()).append(",");
                    }
                }
            } else if (field.isAnnotationPresent(RefColumn.class)) {
                sb.append(this.refSql((RefColumn)field.getAnnotation(RefColumn.class)));
                sb.append(" as ").append(field.getName()).append(",");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(" from ").append(this.getTableName(obj));
        if (StringUtils.isNotEmpty(where)) {
            sb.append(where);
        } else {
            sb.append(" where 1=1 ");
        }

        String sql = sb.toString();
        this.log.info("SQL: {}", sql);
        return sql;
    }

    private String refSql(RefColumn fieldAnno) {
        if (StringUtils.isEmpty(fieldAnno.refSql())) {
            StringBuilder sb = new StringBuilder("( select ");
            sb.append(fieldAnno.refCol()).append(" from ").append(fieldAnno.refTb()).append(" as t where ").append(" t.").append(fieldAnno.refWhereCol()).append("= ").append(fieldAnno.masterCol()).append(" )");
            return sb.toString();
        } else {
            return fieldAnno.refSql();
        }
    }

    public String getWhereByObj(Object obj, Boolean isNeedPage, Boolean checkLike, String orderList) {
        Class clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();
        StringBuilder sbColumn = new StringBuilder();
        Field[] var8 = fields;
        int var9 = fields.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            Field field = var8[var10];

            try {
                if (field.isAnnotationPresent(Column.class)) {
                    Column fieldAnno = (Column)field.getAnnotation(Column.class);
                    if (field.get(obj) != null) {
                        if (StringUtils.isNotEmpty(fieldAnno.column())) {
                            sbColumn.append(fieldAnno.column());
                        } else {
                            sbColumn.append(field.getName());
                        }

                        if (checkLike.booleanValue() && fieldAnno.isUseLike()) {
                            sbColumn.append(" like ");
                            sbColumn.append("'%").append(String.valueOf(field.get(obj))).append("%'");
                        } else {
                            sbColumn.append(" = ");
                            sbColumn.append("'").append(String.valueOf(field.get(obj))).append("'");
                        }

                        sbColumn.append(" and ");
                    }
                }
            } catch (IllegalAccessException var13) {
                this.log.error("获取where失败");
            }
        }

        StringBuilder sb = new StringBuilder(" where ");
        if (sbColumn.length() > 0) {
            sbColumn.append(" 1=1 ");
            sb.append(sbColumn);
        } else {
            sb.append(" 1=1 ");
        }

        if (StringUtils.isNotEmpty(orderList)) {
            sb.append(" order by ").append(orderList);
        }

        if (isNeedPage.booleanValue()) {
            if (!StringUtils.isEmpty(((Page)obj).getPageSize()) && !StringUtils.isEmpty(((Page)obj).getPageNo())) {
                sb.append(" limit ").append(((Page)obj).getStartRecode()).append(" , ").append(((Page)obj).getPageSize());
            } else {
                sb.append(" limit 0,10");
            }
        }

        return sb.toString();
    }

    public String getInsertOne(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        return this.getInsertCore(clz, 1);
    }

    public String getInsertAll(Map<String, Object> para) throws Exception {
        Object obj = para.get("list");
        if (obj instanceof List) {
            List<Object> clz = (List)obj;
            return this.getInsertCore(clz.get(0), clz.size());
        } else {
            throw new Exception("list参数错误");
        }
    }

    public String getUpdateByWhere(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        Object where = para.get("where");
        if (where != null) {
            return this.getUpdateCore(clz, String.valueOf(where));
        } else {
            throw new Exception("where参数错误");
        }
    }

    public String getUpdate(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        return this.getUpdateCore(clz, (String)null);
    }

    public String getDeleteByWhere(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        return this.getDeleteCore(clz);
    }

    public String getSelectByWhere(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        Object where = para.get("where");
        return where != null ? this.getSelectCore(clz, String.valueOf(where)) : this.getSelectCore(clz, " where 1=1");
    }

    public String getSelectPageList(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        String order = para.get("order") == null ? null : String.valueOf(para.get("order"));
        return this.getSelectCore(clz, this.getWhereByObj(clz, true, true, order));
    }

    public String getSelectNoPageList(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        String order = para.get("order") == null ? null : String.valueOf(para.get("order"));
        return this.getSelectCore(clz, this.getWhereByObj(clz, false, true, order));
    }

    public String selectCount(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        StringBuilder count = new StringBuilder("select count(1) from ");
        count.append(this.getTableName(clz)).append(this.getWhereByObj(clz, false, true, (String)null));
        String sql = count.toString();
        this.log.info("SQL: {}", sql);
        return sql;
    }

    public String getSelect(Map<String, Object> para) throws Exception {
        Object clz = para.get("clz");
        return this.getSelectCore(clz, this.getWhereByObj(clz, false, false, (String)null));
    }
}
