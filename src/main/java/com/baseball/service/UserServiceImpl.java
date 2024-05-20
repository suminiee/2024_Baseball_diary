package com.baseball.service;

import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.PasswordUpdateRequestDto;
import com.baseball.dto.UserInfoUpdateRequestDto;
import com.baseball.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto) {
        try {
            // 입력한 비밀번호를 암호화하여 유저 정보 찾아오기
            String encryptedCurrentPassword = passwordEncoder.encode(passwordUpdateRequestDto.getCurrentPassword());
            //currentPassword를 encoding하면 DB에 담겨있는 암호화된 비밀번호와 달라서 loginId으로 DB에서 찾아와야함
            Optional<UserInfo> optionalUserInfo = userRepository.findByLoginId(passwordUpdateRequestDto.getLoginId());
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                // DB에서 가져온 암호화된 비밀번호와 입력한 암호화된 비밀번호 비교
                if (passwordEncoder.matches(passwordUpdateRequestDto.getCurrentPassword(), userInfo.getPassword())) {
                    // 주어진 DTO에서 새로운 비밀번호 추출하여 업데이트
                    userInfo.updatePassword(passwordEncoder, passwordUpdateRequestDto.getNewPassword());

                    // 업데이트 된 정보 저장
                    userRepository.save(userInfo);
                } else {
                    throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
                }
            } else {
                throw new IllegalArgumentException("loginId가" + passwordUpdateRequestDto.getLoginId() +"인 계정이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            log.error("유저 비밀번호 변경 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("유저 비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }

    @Override
    public void updateUserInfo(UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        try {
            Optional<UserInfo> optionalUserInfo = userRepository.findByLoginId(userInfoUpdateRequestDto.getLoginId());
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                userInfo.setEmail(userInfoUpdateRequestDto.getEmail());
                userInfo.setNickname(userInfoUpdateRequestDto.getNickname());

                //db에 수정내용 저장
                userRepository.save(userInfo);
            } else {
                throw new IllegalArgumentException("loginId가" + userInfoUpdateRequestDto.getLoginId() +"인 계정이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            log.error("유저 정보변경중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("유저 정보변경중 오류 발생 " + e.getMessage());
        }
    }

    @Override
    public void updateMyTeam(String loginId, String myTeam) {
        try {
            Optional<UserInfo> optionalUserInfo = userRepository.findByLoginId(loginId);
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                userInfo.setMyTeam(myTeam);

                //db에 수정내용 저장
                userRepository.save(userInfo);
            } else {
                throw new IllegalArgumentException("loginId가" + loginId + "인 계정이 존재하지 않습니다.");

            }
        } catch (Exception e) {
            log.error("응원 팀 변경중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("응원 팀 변경중 오류 발생 " + e.getMessage());
        }
    }

    @Override
    public Long findUserId(String loginId) {
        try {
            Optional<UserInfo> optionalUserInfo = userRepository.findByLoginId(loginId);
            if (optionalUserInfo.isPresent()){
                UserInfo userInfo = optionalUserInfo.get();
                return userInfo.getUserId();
            } else {
                throw new IllegalArgumentException("loginId가" + loginId + "인 계정이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            log.error("userId 검색 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("userId 검색 중 오류 발생 " + e.getMessage());
        }
    }
}
