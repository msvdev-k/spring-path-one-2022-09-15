package org.msvdev.ee.controller;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.dto.UserDto;
import org.msvdev.ee.entity.User;
import org.msvdev.ee.exception.NotFoundException;
import org.msvdev.ee.mapper.UserMapper;
import org.msvdev.ee.repository.RoleRepository;
import org.msvdev.ee.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;


    @GetMapping
    public String listPage(Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {

        Page<UserDto> userDtoPage = userService.findWithFilter(page, pageSize)
                .map(userMapper::entityToDto);

        model.addAttribute("users", userDtoPage);

        return "user_list";
    }


    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id,
                           Model model) {

        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute(
                "user",
                userMapper.entityToDto(
                        userService.findById(id).orElseThrow(
                                () -> new NotFoundException("Пользователь не найден. ID: " + id))
                )
        );
        return "user_form";
    }


    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", new UserDto());
        return "user_form";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserDto userDto,
                         BindingResult result,
                         Model model) {

        model.addAttribute("roles", roleRepository.findAll());

        if (result.hasErrors()) {
            return "user_form";
        }

        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            result.rejectValue("password", "", "Пароли не совпадают");
            return "user_form";
        }

        User user = userMapper.dtoToEntity(userDto);
        userService.save(user);

        return "redirect:/users";
    }


    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }


}