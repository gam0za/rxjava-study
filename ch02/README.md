2.1 Observable 클래스
* RxJava에 관하여 https://poqw.github.io/RxJava2_1/
* RxJava 1.x - Observable  || Rxjava 1.x -> Observable, maybe, flowable
* 옵서버 패턴을 구현 
    * 객체의  상태 변화를 관차하는 관찰자(옵서버)목록을 객체에 등록
    * 상태 변화가 있을때마다 메서드를 호출하여 객체가 직접 목록의 각 옵서버에게 변화를 알려줌
* Observable 뜻?
    * 관찰자(Observer)가 관찰하는 대상
    * 현재는 관찰되지 않았지만 이론을 통해서 앞으로 관찰할 가능성
    * Ex) onClick() : 사용자가 버튼을 누르면 버튼에 미리 등록해 둔 메서드를 호출해 원하는 처리를 한다.
* RxJava의 Observable은 세가지 알림을 구독자에게 전달함
    * onNext: Observable이 데이터의 발행을 알림
    * onComplete: 모든 데이터의 발행이 완료됐음을 알림, 한 번만 발생함
    * onError: Observable의 실행을 종료시킴
    
2.1.1 just() 함수
* 인자로 넣은 데이터를 차례로 발행하려고 Observable을 생성
* 최대 10개 까지의 인자 값을 넣을 수 있음 (타입은 같아야함)

2.1.2  Subscribe()함수와 disposable 객체
* 내가 동작시키기 원하는 것을 사전에 정의해둔 다음 실제 그것이 실행되는 시점을 조절
* Just() 팩토리 함수로 데이터 흐름을 정의한 후 subscribe()함수를 호출해 실제 데이터를 발행함
* 인자에 따라 처리하는 이벤트(onNext, onComplete, onError)가 다름 
* Disposable 인터페이스의 객체를 리턴함
* Disposable : RxJava 1.x의 Subscription 객체에 해당
* dispose() 
    * observable에게 더 이상 데이터를 발행하지 않도록 구독을 해지하는 함수
    * observable이 onComplete 알림을 보냈을 때 자동으로 호출함
    * isDisposed() : Observable이 데이터를 발행하지 않는지 확인하는 함수
    
    
2.1.3 create()함수
* Just()는 데이터를 인자로 넣으면 자동으로 알림 이벤트가 발생하지만, create()함수는 onNext, onComplete, onError 같은 알림을 개발자가 직접 호출
* 차가운 Observable: source변수를 따로 분리함으로서 subscribe()함수를 호출하지 않으면 아무것도 출력하지 않는다
* 메서드 레퍼런스
    * System.out::println -> data->System.out.println(data)를 람다식으로 줄인것
* RxJava에 익숙한 사용자만 활용하도록 권고

2.1.4  fromArray()함수
* just(), create()는 단일 데이터만 다룸
* 단일 데이터가 아닐 때는 fromXXX() 계열 함수를 사용함
* 배열이 들어있는 데이터를 처리할 때 사용함
* int[] 배열 처리
*  RxJava에서  int배열을 인식시키려면 Integer[]로 변환해야함
* int[] intArray = {}
* Observable.fromArray(toIntegerArray(intArray)

2.1.5 fromIterable() 함수
* Observable을 만드는 다른 방법은 Iterable 인터페이스를 구현한 클래스에서 Observable 객체를 생성하는 것
* Iterable 인터페이스
    * 반복자를 반환함, 어떤 데이터가 있는지와 그 값을 얻어오느 것만 관여할 뿐 특정 데이터 타입에 의존하지 않는다.
    * hasNext(), next() 메서드
*  ArrayList, ArrayBlockingQueue, HashSet, LinkedList, Stack,TreeSet, Vector

2.1.6 fromCallable()함수
* 동시성 API인 Callable 인터페이스

2.1.7 fromFuture() 함수
* 동시성 API로 비동기 계산의 결과를 구할 때 사용한다
* Executor 인터페이스를 구현한 클래스에 Callable 객체를 인자로 넣어 Future 객체를 반환
* get() 메서드를 호출하면 Callable 객체에서 구현한 계산 결과가 나올 때까지 블로킹 된다.
    * 블로킹: 결과값이 나올때까지 기다린다.
    
2.1.8 fromPublisher() 함수
* 자바 9의 표준인 Flow API의 일부

2.2 Single 클래스
* 오직 1개의 데이터만 발행하도록 한정 (한가지 값 or 오류 알림)
    * 기존 Observable은 연속된 값 발행
* 결과가 유일한 서버 API 를 호출할 때 유용하게 사용
* 데이터 하나가 발행과 동시에 종료(onSuccess) 된다
* onNext()와 onComplete()함수가 onSuccess() 함수로 통합된 것
* 라이프 사이클 함수 : 
    * onSuccess() :Single은 자신이 배출하는 하나의 값을 이 메서드를 통해 전달한다
    *  onError() :Single은 항목을 배출할 수 없을 때 이 메서드를 통해 Throwable 객체를 전달한다
* 참고 : http://reactivex.io/documentation/ko/single.html

2.2.1 just() 함수
* Observable과 같은 방법

2.2.2 Observable에서 Single 클래스 사용
* Observable에서 변환해서 Single 클래스를 사용할 수 있음
* 1) 기존 Observable에서  Single 객체로 변환하기 
    * Single.fromObservable(source)
    * onSuccess() 이벤트를 호출한 후 종료함
* 2) single() 함수를 호출해 Single 객체 생성하기
    * Single()함수는 디폴트 값을 인자로 가짐, Observable에서 값이 발행되지 않을 때도 인자로 넣은 기본값을 대신 발행
* 3) first()함수를 호출해 Single 객체 생성하기
    * 여러 개의 데이터를 발행할 수 있는 Observable 을  Single객체로 변환하는 것
    * 하나 이상의 데이터를 발행하더라고 첫 번째 데이터 발행 후 onSuccess 이벤트가 발생
* 4) empty Observable에서 Single 객체 생성하기
    * 값이 발행되지 않을때도 기본 값을 갖는 Single 객체로 변환할 수 있음
* 5) take() 함수에서 Single 객체 생성하기

2.2.3 Single 클래스의 올바른 사용 방법
* Just()함수에 여러 값을 넣을 경우 error발생

2.3 Maybe 클래스
* Single클래스와 마찬가지로 최대 데이터 하나를 가질 수 있지만, 데이터 발행 없이 바로 데이터 발생을 완료 할 수도 있다
* 0, 1개 완료 가능
* Single 클래스에서 onComplete 이벤트가 추가된 형태
* Maybe 객체 생성
    * Maybe 클래스를 이용
    * Observable의 특정 연산자를 통해 생성
        * elementAt(), firstElement(), flatMapMaybe(), lastElement(), reduce(), singleElement()
        
2.4 뜨거운 Observable
* 차가운 Observable
    * 냉장고에 들어있는 냉동식품!
    * Just, fromIterable() 함수를 호출해도 옵서버가 subscribe() 함수를 호출하여 구독하지 않으면 데이터를 발행하지 않음
    * 게으른 접근법
    * 구독자가 구독하면 준비된 데이터를 처음부터 발행
    * Ex) 웹 요청, 데이터베이스 쿼리, 파일 읽기
* 뜨거운 Observable 
    * 구독자의 존재 여부와 관계없이 데이터를 발행
    * 여러 구독자 고려 가능 (서버 APi 호출해서 날씨 정보, 지역 정보, 시간 정보 등 결과 데이터 여러 개를 가져오는 경우)
    * 구독자로서는 Observable에서 발행하는 데이터를 처음부터 모두 수신할 것으로 보장할 수 없음
    * 구독한 시점부터 Observable에서 발행한 값을 받음
    * Ex) 마우스 키보드 시스템 이벤트, 센서 데이터, 주식 가격
    * 예를 들어서, 온도 습도 센서의 데이터를 처리하는 앱이라면 최근의 온도, 습도만 사용자에게 표시하면 된다.
    * 배압을 고려해야함 
        * Observable에서 데이터를 발행하는 속도와 구독자가 처리하는 속도의 차이가 큰 경우 주의!
        * Flowable 이라는 특화 클래스에서 배압을 처리
* 차 -> 뜨 Observable 객체 변환 방법
    * Subject 객체 만들기
    * ConnectableObservable 클래스 활용
    
2.5 Subject 클래스
* 차 -> 뜨 변환
* Observable 속성과 구독자의 속성 모두 가지고 있음
* 데이터를 발행할 수 도 있고, 발행된 데이터를 바로 처리할 수도 있다

2.5.1 AsyncSubject 클래스
* Observable에서 발행한 마지막 데이터를 얻어올 수 있는 Subject 클래스
* 완료되기 전 마지막 데이터에만 관심, 이전 데이터는 무시!
* 만약 Observable이 아무 값도 배출하지 않으면 AsyncSubject 역시 아무 값도 배출하지 않음
* 발행, 구독 모두 가능

2.5.2 BehaviorSubject 클래스
* 구독자가 구독을 하면 가장 최근 값 혹은 기본 값을 넘겨주는 클래스
* Ex) 온도 센서에서 값을 받아올 때 가장 최근 온도 값을 받아오는 동 작, 초기값 반환하는 경우
* createDefault() 함수로 기본 값을 생성함

2.5.3 PublishSubject 클래스
* 구독자가 subscribe() 함수를 호출하면 값을 발행하기 시작함
* 해당 시간에 발생한 데이터를 그대로 구독자에게 전달

2.5.4 ReplaySubject 클래스
* Subject 클래스의 목적: 뜨거운 Observable을 활용하는 것
* 구독자가 새로 생기면 항상 데이터의 처음부터 끝까지 발행하는 것을 보장해줌
* Ex) 테이프로 전체 내용을 녹음해두었다가 새로운 사람이 들어오면 처음부터 들려주는 것
* 모든 데이터 내용을 저장해두는 과정 중 메모리 누수가 발생할 가능성을 염두에 두고 주의!

2.6 ConnectableObservable 클래스
* 차 -> 뜨 Observable 변환
* Observable을 여러 구독자에게 공유할 수 있으므로 원 데이터 하나를 여러 구독자에게 동시에 전달할 때 사용
* Subscribe() 함수를 호출해도 아무 동작이 일어나지 않는다
* Connect() 함수는 호출한 시점부터 subscribe() 함수를 호출한 구독자에게 데이터를 발행
* ConnectableObservable 객체 생성하기
    * Observable에 publish() 함수를 호출
    * 여러 구독자에게 데이터를 발행하기 위해 connect() 함수를 호출하기 전까지 데이터 발행을 유예하는 역할
    
체크할 부분
* 비동기 처리, 쓰레드, sleep, 
* Java 9
* 자바 와일드카드  https://palpit.tistory.com/668
* 
