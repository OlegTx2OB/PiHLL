package com.example.papadoner.service.impl;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.mapper.UserMapper;
import com.example.papadoner.model.Order;
import com.example.papadoner.model.User;
import com.example.papadoner.repository.OrderRepository;
import com.example.papadoner.repository.UserRepository;
import com.example.papadoner.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository mUserRepository;
    private final OrderRepository mOrderRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           OrderRepository orderRepository) {
        this.mUserRepository = userRepository;
        this.mOrderRepository = orderRepository;
    }

    @Override
    public void createUser(User user, @Nullable Set<Long> orderIds) {
        user = setOrders(user, orderIds);
        UserMapper.toDto(mUserRepository.save(user));
    }

    @Override
    public UserDto getUserById(long id) {
        return UserMapper.toDto(mUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found")));
    }

    @Override
    public UserDto updateUser(long id, User newUser, @Nullable Set<Long> orderIds) {
        newUser = setOrders(newUser, orderIds);

        Optional<User> optionalOldUser = mUserRepository.findById(id);
        if (optionalOldUser.isPresent()) {
            User oldUser = optionalOldUser.get();
            newUser.setId(oldUser.getId());
            return UserMapper.toDto(mUserRepository.save(newUser));
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public void deleteUser(long id) {
        mUserRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.toDtos(mUserRepository.findAll());
    }

    @Override
    public List<UserDto> findUsersWithMoreOrdersThan(int count) {
        return UserMapper.toDtos(mUserRepository.findUsersWithMoreOrdersThan(count));
    }

    private User setOrders(User user, Set<Long> orderIds) {
        if (orderIds != null) {
            List<Order> orders = new ArrayList<>();
            for (long id : orderIds) {
                orders.add(
                        mOrderRepository.findById(id).
                                orElseThrow(() -> new EntityNotFoundException(
                                        "Order with id " + id + " not found")));
            }
            user.setOrders(orders);
        }
        return user;
    }
}
