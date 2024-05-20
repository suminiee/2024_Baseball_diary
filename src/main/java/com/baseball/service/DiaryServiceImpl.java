package com.baseball.service;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.DiarySaveRequestDto;
import com.baseball.repository.DiaryRepository;
import com.baseball.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class DiaryServiceImpl implements DiaryService{

    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Long saveDiaryInfo(DiarySaveRequestDto diarySaveRequestDto, Long userId) {
       try {
           UserInfo userInfo = userRepository.findById(userId)
                   .orElseThrow(() -> new RuntimeException("User not found"));

           DiaryInfo diaryInfo = DiaryInfo.builder()
                   .mvp(diarySaveRequestDto.getMvp())
                   .home(diarySaveRequestDto.getHome())
                   .away(diarySaveRequestDto.getAway())
                   .homeScore(diarySaveRequestDto.getHomeScore())
                   .awayScore(diarySaveRequestDto.getAwayScore())
                   .gameDate(diarySaveRequestDto.getGameDate())
                   .stadium(diarySaveRequestDto.getStadium())
                   .watch(diarySaveRequestDto.getWatch())
                   .userId(userInfo)
                   .time(diarySaveRequestDto.getTime())
                   .build();
           diaryRepository.save(diaryInfo);
       } catch (Exception e) {
           log.error("diaryInfo 저장 중 오류 발생: {}", e.getMessage());
           throw new RuntimeException("diaryInfo 저장 중 오류 발생" + e.getMessage());
       }
        return null;
    }
}
