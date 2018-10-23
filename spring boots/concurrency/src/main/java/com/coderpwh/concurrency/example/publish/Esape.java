package com.coderpwh.concurrency.example.publish;

import com.coderpwh.concurrency.annoations.NotRecommend;
import com.coderpwh.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Esape {

    private int thisCanBeEscape = 0;

    public Esape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            LoggerFactory.getLogger("").info("{}", Esape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {

        new Esape();
    }
}
