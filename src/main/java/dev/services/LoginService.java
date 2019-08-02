package dev.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import dev.domains.User;
import dev.exceptions.AppException;
import dev.utils.DbUtils;

public class LoginService {

	public Optional<User> connect(String login, String password) {

		List<User> results = new DbUtils().executeSelect(String.format("select * from user where login='%s'", login),
				resultSet -> new DbUtils().resultSetToUser(resultSet));

		if (results.size() > 1) {
			throw new AppException("at least 2 users with same login");
		}

		for (int i = 0; i < results.size(); i++) {
			if (!BCrypt.checkpw(password, results.get(i).getPassword())) {
				results.remove(i);
			}
		}

		return results.stream().findAny();
	}

}