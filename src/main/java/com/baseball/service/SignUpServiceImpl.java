package com.baseball.service;

import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.IsDuplicatedIdRequestDto;
import com.baseball.dto.IsDuplicatedNicknameRequestDto;
import com.baseball.dto.SignUpRequestDto;
import com.baseball.repository.SignUpRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional //실패시 db rollback하는 기능을 함
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    SignUpRepository signUpRepository;

    private final PasswordEncoder passwordEncoder;
    public SignUpServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(SignUpRequestDto signUpRequestDto) {
        try{
            UserInfo userInfo = UserInfo.builder()
                    .loginId(signUpRequestDto.getLoginId())
                    .password(passwordEncoder.encode(signUpRequestDto.getPassword()))//암호화하여 db에 집어넣기
                    .email(signUpRequestDto.getEmail())
                    .nickname(signUpRequestDto.getNickname())
                    .name(signUpRequestDto.getName())
                    .myTeam(signUpRequestDto.getMyTeam())
                    .build();
            signUpRepository.save(userInfo);

        } catch (Exception e) {
            log.error("SignUp(UserInfo)저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("SignUp(UserInfo)저장 중 오류 발생" + e.getMessage());
        }
    }

    @Override
    public boolean isDuplicatedId(IsDuplicatedIdRequestDto isDuplicatedIdRequestDto) {
        //유저 정보 조회
        UserInfo existUser = signUpRepository.findByLoginId(isDuplicatedIdRequestDto.getLoginId());
        return existUser != null; //존재한다면 true, 존재하지 않는다면 false가 return됨.
    }

    @Override
    public boolean isDuplicatedNickname(IsDuplicatedNicknameRequestDto isDuplicatedNicknameRequestDto) {
        //유저 정보 조회
        UserInfo existUser = signUpRepository.findByNickname(isDuplicatedNicknameRequestDto.getNickname());
        return existUser != null;
    }
}
