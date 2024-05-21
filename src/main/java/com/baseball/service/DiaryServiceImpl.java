package com.baseball.service;

import com.baseball.domain.entity.*;
import com.baseball.dto.DiarySaveRequestDto;
import com.baseball.dto.LineUpNameSaveRequestDto;
import com.baseball.dto.LineUpPositionSaveRequestDto;
import com.baseball.dto.ScoreSaveRequestDto;
import com.baseball.repository.*;
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
    @Autowired
    LineUpPositionRepository lineUpPositionRepository;
    @Autowired
    ScoreRepository scoreRepository;

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

    @Override
    public void saveLineUpPositionInfo(LineUpPositionSaveRequestDto lineUpPositionSaveRequestDto, Long diaryId, Long userId) {
        try {
            UserInfo userInfo = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            DiaryInfo diaryInfo = diaryRepository.findById(diaryId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            LineUpPositionInfo lineUpPositionInfo = LineUpPositionInfo.builder()
                    .userId(userInfo)
                    .diaryId(diaryInfo)
                    .position1(lineUpPositionSaveRequestDto.getPosition1())
                    .position2(lineUpPositionSaveRequestDto.getPosition2())
                    .position3(lineUpPositionSaveRequestDto.getPosition3())
                    .position4(lineUpPositionSaveRequestDto.getPosition4())
                    .position5(lineUpPositionSaveRequestDto.getPosition5())
                    .position6(lineUpPositionSaveRequestDto.getPosition6())
                    .position7(lineUpPositionSaveRequestDto.getPosition7())
                    .position8(lineUpPositionSaveRequestDto.getPosition8())
                    .position9(lineUpPositionSaveRequestDto.getPosition9())
                    .build();
            lineUpPositionRepository.save(lineUpPositionInfo);
        } catch (Exception e) {
            log.error("lineUpNamePosition 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("lineUpNamePosition 저장 중 오류 발생" + e.getMessage());
        }
    }

    @Override
    public void saveScoreInfo(ScoreSaveRequestDto scoreSaveRequestDto, Long diaryId, Long userId) {
        try {
            UserInfo userInfo = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            DiaryInfo diaryInfo = diaryRepository.findById(diaryId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            ScoreInfo scoreInfo = ScoreInfo.builder()
                    .userId(userInfo)
                    .diaryId(diaryInfo)
                    .inning1(scoreSaveRequestDto.getInning1())
                    .inning2(scoreSaveRequestDto.getInning2())
                    .inning3(scoreSaveRequestDto.getInning3())
                    .inning4(scoreSaveRequestDto.getInning4())
                    .inning5(scoreSaveRequestDto.getInning5())
                    .inning6(scoreSaveRequestDto.getInning6())
                    .inning7(scoreSaveRequestDto.getInning7())
                    .inning8(scoreSaveRequestDto.getInning8())
                    .inning9(scoreSaveRequestDto.getInning9())
                    .inning10(scoreSaveRequestDto.getInning10())
                    .inning11(scoreSaveRequestDto.getInning11())
                    .inning12(scoreSaveRequestDto.getInning12())
                    .run(scoreSaveRequestDto.getRun())
                    .hit(scoreSaveRequestDto.getHit())
                    .error(scoreSaveRequestDto.getError())
                    .balls(scoreSaveRequestDto.getBalls())
                    .type(scoreSaveRequestDto.getType())
                    .build();
            scoreRepository.save(scoreInfo);
        } catch (Exception e) {
            log.error("ScoreInfo 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("ScoreInfo 저장 중 오류 발생" + e.getMessage());
        }
    }
}
