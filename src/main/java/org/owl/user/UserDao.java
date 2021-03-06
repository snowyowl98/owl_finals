package org.owl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	static final String SELECT_BY_LOGIN = "SELECT userId, name, email, password FROM user WHERE (email,password) = (?,sha2(?,256))";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	final RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);
	
	/**
	 * 이메일과 비밀번호로 멤버 가져오기. 로그인 할 때 사용한다.
	 */
	public User selectByLogin(String email, String password) {
		return jdbcTemplate.queryForObject(SELECT_BY_LOGIN, userRowMapper,
				email, password);
	}
}