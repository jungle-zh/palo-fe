package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaTablet;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaTabletMapper {
    @Delete("delete from meta_tablet where tablet_id = #{tabletId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long tabletId);

    @Insert("insert into meta_tablet (tablet_id, checked_version, checked_version_hash," +
            "is_consistent, replica_id_list)" +
            "values (#{tabletId,jdbcType=BIGINT}, #{checkedVersion,jdbcType=BIGINT}, #{checkedVersionHash,jdbcType=BIGINT}," +
            "#{isConsistent,jdbcType=INTEGER}, #{replicaIdList,jdbcType=VARCHAR})")
    int insert(MetaTablet record);

    @Insert("<script>"  +
            "insert into meta_tablet (tablet_id, checked_version, checked_version_hash," +
            "is_consistent, replica_id_list)" +
            "values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
            "      (#{item.tabletId,jdbcType=BIGINT}, #{item.checkedVersion,jdbcType=BIGINT}, #{item.checkedVersionHash,jdbcType=BIGINT}," +
            "      #{item.isConsistent,jdbcType=INTEGER}, #{item.replicaIdList,jdbcType=VARCHAR})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<MetaTablet> lst);

    int insertSelective(MetaTablet record);

    MetaTablet selectByPrimaryKey(Long tabletId);

    int updateByPrimaryKeySelective(MetaTablet record);

    int updateByPrimaryKey(MetaTablet record);
}