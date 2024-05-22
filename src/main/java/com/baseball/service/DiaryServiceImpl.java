package com.baseball.service;

import com.baseball.domain.entity.*;
import com.baseball.dto.*;
import com.baseball.repository.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public DiaryResponseDto findDiaryByDiaryId(Long diaryId) {
        Optional<DiaryInfo> diaryInfoOptional = diaryRepository.findById(diaryId);
        return diaryInfoOptional.map(DiaryResponseDto::new).orElse(null);
    }

    @Override
    public LineUpNameResponseDto findLineUpNameByDiaryId(Long diaryId) {
        Optional<LineUpNameInfo> lineUpNameResponseDtoOptional = lineUpNameRepository.findByDiaryId(diaryRepository.getById(diaryId));
        return lineUpNameResponseDtoOptional.map(LineUpNameResponseDto::new).orElse(null);
    }

    @Override
    public LineUpPositionResponseDto findLineUpPositionByDiaryId(Long diaryId) {
        Optional<LineUpPositionInfo> lineupPositionResponseDtoOptional = lineUpPositionRepository.findByDiaryId(diaryRepository.getById(diaryId));
        return lineupPositionResponseDtoOptional.map(LineUpPositionResponseDto::new).orElse(null);
    }

    @Override
    public ScoreResponseDto findScoreByDiaryId(Long diaryId) {
        Optional<ScoreInfo> scoreInfoOptional = scoreRepository.findByDiaryId(diaryRepository.getById(diaryId));
        return scoreInfoOptional.map(ScoreResponseDto::new).orElse(null);
    }

    @Override
    public void updateDiary(DiarySaveRequestDto diarySaveRequestDto, Long diaryId) {
        try {
            Optional<DiaryInfo> optionalDiaryInfo = diaryRepository.findById(diaryId);
            if (optionalDiaryInfo.isPresent()) {
                DiaryInfo diaryInfo = optionalDiaryInfo.get();

                //주어진 DTO에서 새로운 정보 추출하여 업데이트
                diaryInfo.setHome(diarySaveRequestDto.getHome());
                diaryInfo.setAway(diarySaveRequestDto.getAway());
                diaryInfo.setMvp(diarySaveRequestDto.getMvp());
                diaryInfo.setHomeScore(diarySaveRequestDto.getHomeScore());
                diaryInfo.setAwayScore(diarySaveRequestDto.getAwayScore());
                diaryInfo.setTime(diarySaveRequestDto.getTime());
                diaryInfo.setStadium(diaryInfo.getStadium());
                diaryInfo.setWatch(diaryInfo.getWatch());

                //업데이트 된 정보 저장
                diaryRepository.save(diaryInfo);

            } else {
                throw new IllegalArgumentException("일기 내용을 찾을 수 없습니다. ID: " + diaryId);

            }
        } catch (Exception e) {
            log.error("야구 일기 정보 업데이트 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("야구 일기 정보 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public void updateLineUpName(LineUpNameResponseDto lineUpNameResponseDto, Long diaryId) {
        try {
            DiaryInfo diaryInfo = diaryRepository.findById(diaryId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Optional<LineUpNameInfo> optionalLineUpNameInfo = lineUpNameRepository.findByDiaryId(diaryInfo);

            if (optionalLineUpNameInfo.isPresent()) {
                LineUpNameInfo lineUpNameInfo = optionalLineUpNameInfo.get();

                //주어진 DTO에서 새로운 정보 추출하여 업데이트
                lineUpNameInfo.setHitter1(lineUpNameInfo.getHitter1());
                lineUpNameInfo.setHitter2(lineUpNameInfo.getHitter2());
                lineUpNameInfo.setHitter3(lineUpNameInfo.getHitter3());
                lineUpNameInfo.setHitter4(lineUpNameInfo.getHitter4());
                lineUpNameInfo.setHitter5(lineUpNameInfo.getHitter5());
                lineUpNameInfo.setHitter6(lineUpNameInfo.getHitter6());
                lineUpNameInfo.setHitter7(lineUpNameInfo.getHitter7());
                lineUpNameInfo.setHitter8(lineUpNameInfo.getHitter8());
                lineUpNameInfo.setHitter9(lineUpNameInfo.getHitter9());
                lineUpNameInfo.setPitcher(lineUpNameInfo.getPitcher());

                //업데이트 된 정보 저장
                lineUpNameRepository.save(lineUpNameInfo);
            } else {
                throw new IllegalArgumentException("일기 내용을 찾을 수 없습니다. ID: " + diaryId);
            }
        } catch (Exception e) {
            log.error("야구 일기 라인업 이름 업데이트 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("야구 일기 라인업 이름 업데이트 중 오류 발생: " + e.getMessage());
        }
    }
}
