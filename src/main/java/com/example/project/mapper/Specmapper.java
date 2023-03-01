package com.example.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.project.dto.Spec;

@Mapper
public interface Specmapper {

   @Select("SELECT cost FROM category WHERE name = #{spec.name} AND (expr IS NULL OR ((#{spec.width} > width AND expr = 'gt') OR (#{spec.width} <= width AND expr = 'lte')))LIMIT 1")
   public String getCost(@Param("spec") Spec spec);
}