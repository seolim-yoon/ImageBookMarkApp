# WebToonSearchApp
  네이버 이미지 api를 활용하여 Main, BookMark, Search, Viewer 화면을 구현하였습니다.

<br/>
   
## Tech
* 언어 : Kotlin
* 디자인 패턴 : MVI
* 사용 라이브러리
  * api 통신 : Retrofit, OkHttp, Kotlin-serialization
  * 의존성 주입 : Hilt
  * 이미지 : Glide
  * Ui : Compose
  * 페이징 : Paging3
  * 페이징 사용 시 로컬 저장용 : Room
  * 화면 이동 : navigation, hilt-navigation

  <br/>

## 프로젝트 구조
* Data
  * DTO 생성
  * api를 받아 Paging3의 RemoteMediator을 사용하여 DB에 저장 후 로컬에 저장된 데이터를 활용하여 각각 스크린에 데이터 노출
  * Repository에서 DTO -> Entity 맵핑
 
* Domain
  * Entity 생성
  * Usecase에서 비즈니스 로직 처리 (메인 리스트 불러오기, 북마크 리스트 불러오기, 검색한 이미지 리스트 불러오기, 북마크 클릭 처리, 이미지 URL 불러오기)
 
* App
  * UiModel 생성
  * ViewModel에서 Usecase를 통해 컴포넌트 리스트 가져온 후, Entity -> UiModel 맵핑
  * UiModel로 변환된 리스트를 페이징과 컴포즈를 사용하여 화면에 노출
  * 페이징의 LoadState에 따라 Loading, List, ErrorScreen 노출
 

<br/>

## Screen 구조 설명
* BaseScreen
  * MainScreen, BookMarkScreen이 바텀 네비게이션으로 구성된 Base 화면
  * Scaffold를 사용하여 Main과 BookMark 화면 한정 Topbar, BottomNavigation을 구현
 
* MainScreen
  * 네이버 이미지 api (query = "만화") 호출하여 페이징을 활용한 리스트 노출
      * api 호출 -> 기존 DB 삭제 후 새로 받은 api 데이터를 DB에 삽입 -> DB에 저장된 리스트 노출
  * 아이템 Click -> ViewerScreen으로 이동 (link 값 전달)
  * 아이템 LongClick -> 북마크 선택 모드로 변경 (checkbox 노출)
  * 북마크에 저장할 데이터를 선택하여 [북마크에 저장] 버튼을 누르면 MainScreen, BookMarkScreen에 반영

* BookMarkScreen
  * MainScreen에서 북마크에 저장한 리스트만 노출 (DB 테이블에서 isBookMark = true인 데이터)
  * 아이템 Click -> ViewerScreen으로 이동 (link 값 전달)
  * 아이템 LongClick -> 북마크 삭제 모드로 변경 (checkbox 노출)
  * 북마크에서 삭제할 데이터를 선택하여 [북마크에서 삭제] 버튼을 누르면 BookMarkScreen, MainScreen에 반영
 
* SearchScreen
  * MainScreen에서 DB에 저장했던 리스트에서 검색할 수 있는 화면
  * 입력한 키워드로 검색한 결과가 없을 경우 -> EmptyScreen
  * 오류가 발생한 경우 -> ErrorScreen
  * 아이템 Click -> ViewerScreen으로 이동 (link 값 전달)

* ViewerScreen
  * MainScreen에서 DB에 저장했던 리스트에서 link 값만 불러와서 리스트로 노출
    
