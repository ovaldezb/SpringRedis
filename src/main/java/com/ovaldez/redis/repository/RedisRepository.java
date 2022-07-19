package com.ovaldez.redis.repository;

import java.util.Map;

import com.ovaldez.redis.domain.Student;

public interface RedisRepository {
	Map<String, Student> findAll();
	Student findById(String id);
	Student save(Student student);
	String delete(String id);

}
