# kotlin-racingcar-precourse

## 목표 및 실행 예시
**자동차 경주 게임 구현**

```
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
pobi,woni,jun
시도할 횟수는 몇 회인가요?
5

실행 결과
pobi : -
woni :
jun : -

pobi : --
woni : -
jun : --

pobi : ---
woni : --
jun : ---

pobi : ----
woni : ---
jun : ----

pobi : -----
woni : ----
jun : -----

최종 우승자 : pobi, jun
```

## 기능목록

1. 경주할 자동차 이름 입력
- 이름은 쉽표(,)를 기준으로 구분
- 각 이름은 5자 이하
    ```
    경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
    pobi,woni,jun
    ```

2. 시도할 게임 횟수 입력
    ```
    시도할 횟수는 몇 회인가요?
    5
    ```

3. 게임 진행
- 입력한 횟수 만큼 진행
- 0~9사이에서 무작위 값을 구함
- 무작위 값이 4 이상일 경우 전진

4. 차수별 실행 결과 출력
    ```
    pobi : --
    woni : ----
    jun : ---
    ```

4. 최종 게임 결과 출력
- 우승자가 여러 명일 경우 쉼표(,)를 이용하여 구분
- 단독 우승자 안내 문구 => `최종 우승자 : pobi`
- 공동 우승자 안내 문구 => `최종 우승자 : pobi, jun`

## 예외처리

1. 경주할 자동차 이름 입력
- 빈 문자열을 입력한 경우
- 자동차 이름이 하나만 입력된 경우
- 이름이 5자가 넘는 경우
  - 공백을 포함하되 이름 양끝의 공백은 포함하지 않음
- 중복된 이름인 경우

2. 게임 횟수 입력
- 빈 문자열을 입력한 경우
- 양의 정수가 아닌 경우

## 기타 요구 사항
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
- `camp.nextstep.edu.missionutils`에서 제공하는 `Randoms` 및 `Console` API를 사용하여 구현해야 한다.
    - Random 값 추출은 `camp.nextstep.edu.missionutils.Randoms`의 `pickNumberInRange()`를 활용한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.
- 프로그램 종료 시 System.exit() 또는 exitProcess()를 호출하지 않는다.