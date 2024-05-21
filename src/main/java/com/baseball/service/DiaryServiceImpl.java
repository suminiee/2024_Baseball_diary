package com.baseball.service;

import com.baseball.domain.entity.DiaryInfo;
import com.baseball.domain.entity.LineUpNameInfo;
import com.baseball.domain.entity.UserInfo;
import com.baseball.dto.DiarySaveRequestDto;
import com.baseball.dto.LineUpNameSaveRequestDto;
import com.baseball.repository.DiaryRepository;
import com.baseball.repository.LineUpNameRepository;
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
    @Autowired
    LineUpNameRepository lineUpNameRepository;

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
//           diaryRepository.save(diaryInfo);
           DiaryInfo savedDiaryInfo = diaryRepository.save(diaryInfo);
           return savedDiaryInfo.getDiaryId();
       } catch (Exception e) {
           log.error("diaryInfo 저장 중 오류 발생: {}", e.getMessage());
           throw new RuntimeException("diaryInfo 저장 중 오류 발생" + e.getMessage());
       }
    }

    @Override
    public void saveLineUpNameInfo(LineUpNameSaveRequestDto lineUpNameSaveRequestDto, Long diaryId, Long userId) {
        try {
            UserInfo userInfo = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            DiaryInfo diaryInfo = diaryRepository.findById(diaryId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            LineUpNameInfo lineUpNameInfo = LineUpNameInfo.builder()
                    .diaryId(diaryInfo)
                    .userId(userInfo)
                    .hitter1(lineUpNameSaveRequestDto.getHitter1())
                    .hitter2(lineUpNameSaveRequestDto.getHitter2())
                    .hitter3(lineUpNameSaveRequestDto.getHitter3())
                    .hitter4(lineUpNameSaveRequestDto.getHitter4())
                    .hitter5(lineUpNameSaveRequestDto.getHitter5())
                    .hitter6(lineUpNameSaveRequestDto.getHitter6())
                    .hitter7(lineUpNameSaveRequestDto.getHitter7())
                    .hitter8(lineUpNameSaveRequestDto.getHitter8())
                    .hitter9(lineUpNameSaveRequestDto.getHitter9())
                    .pitcher(lineUpNameSaveRequestDto.getPitcher())
                    .build();
            lineUpNameRepository.save(lineUpNameInfo);
        } catch (Exception e) {
            log.error("lineUpNameInfo 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("lineUpNameInfo 저장 중 오류 발생" + e.getMessage());
        }
    }
}
