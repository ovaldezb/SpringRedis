package com.ovaldez.redis.repository;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ovaldez.redis.domain.Student;

@Service
public class StudentRepository implements RedisRepository {
	private static final String KEY = "Student";
	private RedisTemplate<String, Student> redisTemplate;
	private HashOperations<String, String, Student> hashOperations;
	
	public  StudentRepository(RedisTemplate<String, Student> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public Map<String, Student> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public Student findById(String id) {
		return hashOperations.get(KEY, id);
	}

	@Override
	public Student save(Student student) {
		hashOperations.put(KEY, UUID.randomUUID().toString(), student);
		return student;

	}

	@Override
	public String delete(String id) {
		hashOperations.delete(KEY, id);
		return id;

	}

}
