package ch03;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class QueryTVSales {
    public static void main(String[] args){
        // Step 1. 데이터 입력
        // 왼쪽에는 상품 이름, 오른쪽은 매출액
        List<Pair<String, Integer>> sales = new ArrayList<>();

        sales.add(Pair.of("TV",2500));
        sales.add(Pair.of("Camera",300));
        sales.add(Pair.of("TV",1600));
        sales.add(Pair.of("Phone",800));

        // Step 2. 매출 데이터 중 TV 매출 필터링
        // TODO: filter()와 map() 를 사용하여 TV매출을 필터링한다.

        // Step 3. TV매출의 합을 구함
        // TODO: reduce()를 사용하여 TV매출의 합을 구한다.

    }
}
