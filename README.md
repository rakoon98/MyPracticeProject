# MyPracticeProject
그냥 이것저것 해보는 프로젝트

# 컴포즈 설명 
- 선언형 프로그래밍 패러다임을 따르는 UI를 그리는 라이브러리 ( Stateless 한 위젯 )
- 기본 레이아웃 : Column, Row, Box, ConstraintLayout, BoxWithConstraint
- Modifier 로 각 레이아웃에 대한 정의 및 컨트롤을 해준다.
  - .backGround 에 Brush 이용하여 그라데이션 등 가능.
  - modifier.alpha 는 작동하지않는다 -> Color.copy(alpha = 0.5f) 식으로 색상에 줘야한다.
  - modifier.border 로 테두리 및 모양, 크기 등을 정할 수 있다.
    - padding 과 적절히 조합하면 겹겹이 테두리도 가능
  - 기존 Drawable 만들어서 shape 등 처리하던걸 modifier 로 처리 가능. -> resource 사용 줄임.
- Scaffold 중요 : Material Component 들을 편한하게 사용할 수 있도록 미리 디자인된 레이아웃
  - snackBar Control 가능 다만 navigation 사용시 어떻게 할지 고민...
- remember
  - Recomposition(재구성) 중요
  - 이 때 state 를 기억해야할 때 쓰임.
- FontFamily
  - 폰트를 각 FontWeight 로 설정해놓고 코드로 설정
  - Text(~~)에 fontFamily 에 해당 FontFamily 리스트를 넣고 FontWeight 설정하면 해당 weight 찾아서 설정됨.
  - 참고 : https://kotlinworld.com/211?category=973277
- Color
  - 16진수를 8진수로 Color 표기
  - 4byte(256 * 256 * 256 * 256) = Color 하나를 의미
  - 기존 color.xml -> Color 객체 사용 변환
- SideEffect ( Composable 범위 밖에서 발생하는 앱 상태에 대한 변경 )
  - 참고 : [https://kotlinworld.com/245?category=974273] 
  - Composable 은 각각의 LifeCycle 을 가지고 있다.
  - Composable 은 단방향으로만 State 로 전달한다.
    * Composable 에서 바깥족의 Composable 의 상태에대한 변경 혹은 Composable 이 아닌 앱 상태에 대한 변경을 주는 경우
      - 양방향 의존성으로 인해 예측 할 수 없는 Effect(효과)가 생길 것이다.
      - 우리는 이를 SideEffect 라고 한다.
      - 외부의 state 에 영향을 만드는 것.
- StateHoisting
  - Stateful 한 Composable 을 Stateless 하도록 만들기 위한 디자인 패턴
  - 자식 Composable 의 state 를 호출부로 끌어올리는것.
    - value, onValueChanged : (T) -> Unit 두개로 끌어올린다.
  - TextField 또한 이와 같이 구현되어있다.

# 참고
1. https://kotlinworld.com/184?category=971009 : 굉장히 잘 정리된 안드로이드 개발 블로그