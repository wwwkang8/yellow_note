# 맛집 검색 서비스 Yellow Note

## ERD 설계
![image](https://github.com/wwwkang8/yellow_note/assets/26863285/38e2f063-8876-4e00-8d5c-d3b4f56377e5)

1) 검색기록 테이블
- 검색키워드 컬럼 : 대량의 기록 데이터이기 때문에 검색시에 INDEX를 설정
- 검색위치 컬럼 : 추후 어느 지역에서 검색을 많이 했는지 마케팅 자료로 사용하기 위해서 추가
- 응답상태코드 : 외부 API의 에러 여부 확인 가능 => 추후 서킷브레이커 패턴에도 적용 가능
- 응답데이터 : 검색어로 어떤 데이터를 응답했는지 확인

2) 인기키워드 테이블
- 카운팅 컬럼 : 키워드를 얼마나 많이 검색했는지 카운트 하는 컬럼

3) 사용자 테이블
- 토큰 : 토큰을 발급하여 유효한 API 요청인지 검증

## API 명세
### [맛집검색 API]
| 메서드 | 요청 URL | 기능 |
|--------|-----------------| -----------|
| GET    |/api/food/     | 맛집 조회   |

Request
| 파라메터 | 타입 | 필수여부 | 설명 |
|--------|----------| ----------|----------|
| keyword|String    |O          |맛집검색에 필요한 키워드|
| sort|String    |X          |리뷰 조회조건(정확도, 리뷰개수)|

Response
| 필드 | 타입 | 필수여부 | 설명 |
|--------|----------------|-----|------------------|
| title  |String    |필수  |업체명|
| address |String   |필수  |주소|
| telephone |String |필수  |전화번호|


### [인기키워드 API]
| 메서드 | 요청 URL | 기능 |
|--------|-----------------| -----------|
| GET    |/api/keyword/     | 인기키워드 조회   |

Request
| 파라메터 | 타입 | 필수여부 | 설명 |
|--------|----------| ----------|----------|
| 없음|    |          |keyword 호출하는 파라메터 없음|


Response
| 필드 | 타입 | 필수여부 | 설명 |
|--------|----------------|-----|------------------|
| rank  |number    |필수  |순위|
| keyword |String   |필수  |키워드|
| count |number   |필수  |키워드가 검색된 횟수|























