package CalculationForTest;

import javax.swing.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import static java.lang.Math.*;

/*
絶対誤差: |x' - x|
相対誤差: |誤差 / 真の値| = |(x' - x) / x|
残差: |f(x')|
*/

public class SolveAlgorithm {

    public static void main(String[] args) {
        SolveAlgorithm s = new SolveAlgorithm(
            1.0e-12, 200,
            //(x) -> x*x*x*x - 13.0/2*x*x*x + 15*x*x - 14*x + 4,
            (x) -> 4*x*x*x - 39.0/2*x*x   + 30*x   - 14,
            (x) -> 12 *x*x - 39*x         * 30
        );
        System.out.println(s.rSecant(-2));
    }

    SolveAlgorithm(double eps, int max, Function<Double, Double> f, Function<Double, Double> df) {
        this.eps = eps;
        this.max = max;
        this.f = f;
        this.df = df;
    }

    private double eps;
    private int max;
    private Function<Double, Double> f;
    private Function<Double, Double> df;
    private String json = "";
    private List<BiConsumer<Double, Integer>> logList = new ArrayList<>();
    private Map<BiConsumer<Double, Integer>,Function<Integer,Boolean>> necklogList = new HashMap<>();
    private void head(String name){
        json = "";
        json += "{\n";
        json += "\t\"" + name + "\":";
        json += "\n\t\t{\n";
    };
    private void neck(double x,int count){
        necklogList.forEach((key,value)->{
            if(value.apply(count)){
                key.accept(x,count);
            }
        });
    }

    private void body(double x,int count){

        for (BiConsumer<Double, Integer> itr : logList) {
            itr.accept(x, count);
        }
        json += "\t\t\t\"loop\": " + count + ",\n";
        json += "\t\t\t\"answer\": " + x + "\n";

    };
    private void foot(){
        json += "\t\t}\n";
        json += "}";
        System.out.println(json);
    };

    private double scfunc(double x1, double x2) {
        return x2 - f.apply(x2) * (x2 - x1) / (f.apply(x2) - f.apply(x1));
    }

    public String getLastJson() {
        return json;
    }

    public void addLog(String key, BiFunction<Double, Integer, String> value) {
        logList.add((x, count) -> {
            json += "\t\t\t\"" + key + "\": " + value.apply(x, count) + ",\n";
        });
    }
    public void addLog(BiFunction<Double, Integer, String> value, Function<Integer,Boolean> appliable) {
        necklogList.put((x, count) -> {
            //if(count==1)json+="\n";
            json += "\t\t\t\"" + count + "\": " + value.apply(x, count) + ",\n";
        }, appliable);
    }


    public double newton(double x) {
        head("newTon");
        int count = 0;
        while (Math.abs(f.apply(x)) > eps) {
            count++;
            if (count >= max) {
                json += "null\n";
                foot();
                return 0;
            }
            x -= f.apply(x) / df.apply(x);
            neck(x,count);
        }
        body(x, count);
        foot();
        return x;
    }

    public double rNewton(double x) {
        head("rNewton");
        int count = 1; // 初期化で一回やってる
        double x1 = x;
        double x2 = x1 - f.apply(x1) / df.apply(x1);
        while (Math.abs((x2 - x1) / x2) > eps) {
            count++;
            if (count >= max) {
                json += "null\n";
                foot();
                return 0;
            }
            x1 = x2;
            x2 = x1 - f.apply(x1) / df.apply(x1);
            neck(x2,count);
        }
        body(x2, count);
        foot();
        return x2;
    }

    public double rSecant(double x) {
        head("rSecond");
        double myx1 = x;
        double myx2 = x + 1.0;
        double myx3 = scfunc(myx1, myx2);
        int count = 1;
        while (Math.abs((myx2 - myx1) / myx2) > eps) {
            count++;
            if (count >= max) {
                json += "null\n";
                foot();
                return 0;
            }
            myx1 = myx2;
            myx2 = myx3;
            myx3 = scfunc(myx1, myx2);
            neck(myx2,count);
        }
        body(myx2, count);
        foot();
        return myx2;
    }

    public double secant(double x) {
        head("secant");
        double myx1 = x;
        double myx2 = x + 1.0;
        double myx3 = scfunc(myx1, myx2);
        int count = 0;
        while (Math.abs(f.apply(myx2)) > eps) {
            count++;
            if (count >= max) {
                json += "null\n";
                foot();
                return 0;
            }
            myx1 = myx2;
            myx2 = myx3;
            myx3 = scfunc(myx1, myx2);
            neck(myx2,count);
        }
        body(myx2, count);
        foot();
        return myx2;
    }

    public double rParallel(double x) {
        head("rParallel");
        int count = 0;
        double x1 = x;
        double x2 = x - f.apply(x) / df.apply(x);
        while (Math.abs((x2 - x1) / x2) > eps) {
            count++;
            if (count >= max) {
                json += "null\n";
                foot();
                return 0;
            }
            x1 = x2;
            x2 = x1 - f.apply(x1) / df.apply(x);
            neck(x2,count);
        }
        body(x2, count);
        foot();
        return x2;
    }

    public double parallel(double x) {
        head("parallel");
        int count = 0;
        double x1 = x;
        while (Math.abs(f.apply(x1)) > eps) {
            count++;
            if (count >= max) {
                json += "null\n";
                foot();
                return 0;
            }
            x1 -= f.apply(x1) / df.apply(x);
            neck(x1,count);
        }
        body(x1, count);
        foot();
        return x1;
    }
}

