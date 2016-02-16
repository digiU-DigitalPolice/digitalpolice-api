package com.example.repository.memory;

import com.example.entity.User;
import com.example.repository.MemoryDAO;
import com.example.repository.definition.UserDAO;

public class UserMemoryDAO extends MemoryDAO<User, Long> implements UserDAO {
}
