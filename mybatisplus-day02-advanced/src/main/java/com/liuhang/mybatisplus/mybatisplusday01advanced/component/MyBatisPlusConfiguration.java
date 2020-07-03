package com.liuhang.mybatisplus.mybatisplusday01advanced.component;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class MyBatisPlusConfiguration {

    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    /**
     * 插件三：乐观锁
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        /**
         * 插件四：多租户 SQL 解析器
         *          配合 分页拦截器 使用
         */
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId(boolean select) {
//                    return new LongValue(1087982257332887553L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                // 租户字段为 manager_id，
//                return "manager_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                return false;
//            }
//        });
//        sqlParserList.add(tenantSqlParser);


        /**
         * 插件五：动态表名 SQL 解析器
         */
//        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
//        HashMap<String, ITableNameHandler> tableNameHandlerHashMap = new HashMap<>();
//        // 将表名为 user_advance 替换成 myTableName.get() 获取的值，这个值是程序员自己设置的
//        tableNameHandlerHashMap.put("user_advance", new ITableNameHandler() {
//            @Override
//            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                  // 如果返回值为null，即没有设置，则表名仍然为 user_advance
//                return myTableName.get();
//            }
//        });
//        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerHashMap);
//        sqlParserList.add(dynamicTableNameParser);


        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                // 过滤自定义查询此时无租户信息约束
                // 配置了过滤器，过滤了selectById方法，则调用该方法时，没有租户信息
                // 注意：该配置对动态表名SQL也起到了过滤作用，即导致动态表名SQL不生效
                if ("com.liuhang.mybatisplus.mybatisplusday01advanced.dao.UserMapper.selectById".equals(ms.getId())) {
                    return true;
                }
                return false;
            }
        });
        return paginationInterceptor;
    }
}
