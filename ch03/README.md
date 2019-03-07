## Chapter 03. 리액티브 연산자

***

> Today Goal : 리액티브 연산자의 종류와 가장 많이 쓰이는 map(), flatMap(), filter(), reduce()에 대해 알아보자!

### 3.0. 리액티브 연산자 종류

* 생성 연산자: 데이터의 흐름을 만들어내는 함수 (create(), just(), fromArray() 등)

* 변환 연산자: 어떤 입력을 받아서 원하는 출력 결과를 얻는 함수 (map(), flatMap())

* 필터 연산자: 입력 데이터 중에 원하는 데이터만 걸러내는 함수 (filter(), first(), take())

* 합성 연산자: 여러 Observable을 조합하는 함수 (한 개의 Observable이 아니라 여러개 생성/조합)

* 오류 처리 연산자: 오류를 처리하는 함수 (onErrorReuturn(), onErrorResumeNext(), Retry())

* 유틸리티 연산자: 비동기 프로그래밍을 지원하기 위한 함수 - 쓰레드 처리! (subscribeOn(), observeOn())

* 조건 연산자: Observable 흐름 제어

* 수학과 집합형 연산자: 수학함수와 연관있는 연산자

* 배압 연산자: 배압 이슈에 대응하는 연산자


### 3.1. map() 함수

> map() 함수는 '변환 연산자'의 종류로, 입력 값을 어떤 함수에 넣어서 원하는 값으로 변환하는 함수이다.

* 자바는 함수를 인자로 넘길 수 없기 때문에 '함수형 인터페이스'를 사용한다!

* map()에 사용되는 함수형 인터페이스는 Function<T,R> 이다. (T: 입력 / R: 반환)

* 함수형 인터페이스 대신 '::' (참조자)를 대신해서 사용할 수 있다. 

* map() 함수는 입력 데이터와 함수를 연결해주는 매개 역할이라고 보면 된다. 


### 3.2. flatMap() 함수

> flatMap() 함수는 map() 함수의 발전 형태로, 결과가 Observable 형태로 나온다. 즉, map() 함수는 일대일 함수라면, flatMap() 함수는 일대다 혹은 일대일 Observable 함수이다. 

* flatMap(Function)

* flatMap(Function, BiFunction)

### 3.3. filter() 함수

> Observable에서 원하는 데이터만 걸러내는 역할을 한다. 말그대로 필터링!!

filter() 함수 대신 사용할 수 있는 함수가 많다.

* first() : 함수로 발행하는 데이터 중 첫번째 항목만 리턴

* last() : 마지막 항목만 리턴

* take(N) : 전체 데이터 중 N개 만큼 리턴(앞에서 부터)

* takeLast(N) : 전체 데이터 중 N개 만큼 리턴(뒤에서 부터)

* skip(N) : 처음 항목 기준으로 N개 제외하고 리턴

* skipLast(N) : 마지막 항목을 기준으로 N개 제외하고 리턴 

여기서 잠깐! Single 클래스는 데이터를 오직 1개만 발행!! => first(), last()에 쓰임


### 3.4. reduce() 함수

> reduce() 함수는 발행한 데이터를 모두 사용하여 어떤 최종 결과 데이터를 합성할 때 활용한다.

* Observable이 아닌 Maybe 클래스 사용!!

* reduce(BiFunction)

* BiFunction<T, U, R>로, 두 개의 입력 인자를 받아 R 타입으로 반환하는 인터페이스이다.

