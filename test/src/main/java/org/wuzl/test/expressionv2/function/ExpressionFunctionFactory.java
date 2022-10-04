package org.wuzl.test.expressionv2.function;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.wuzl.test.expression.ExpressionException;
import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.ExpressionNodeType;
import org.wuzl.test.expressionv2.function.impl.AddDateExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.AddTimeExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.BetweenExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.CurrentDateExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.CurrentDateTimeExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.CurrentTimeExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.DateDiffExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.DateFormatExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.IfNullExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.InExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.LeftLikeExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.LikeExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.NotBetweenExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.NotInExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.NotLikeExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.NotRegexpExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.PowExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.RegexpExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.RightLikeExpressionFunction;
import org.wuzl.test.expressionv2.function.impl.StrToDateExpressionFunction;

/**
 * 表达式函数工厂
 * 
 * @author ziliang.wu
 *
 */
public class ExpressionFunctionFactory {
    private static final Map<String, ExpressionFunction> CACHE = new ConcurrentHashMap<>();
    static {
        ExpressionFunction emptyFunction = new ExpressionFunction() {

            @Override
            public int variableNum() {
                return 0;
            }

            @Override
            public String functionName() {
                return null;
            }

            @Override
            public boolean fixedVariable() {
                return false;
            }

            @Override
            public String conver(List<ExpressionNode> nodes) {
                return null;
            }
        };
        CACHE.put(ExpressionNodeType.IF.getType(), emptyFunction);
        CACHE.put(ExpressionNodeType.ELSE_IF.getType(), emptyFunction);
        CACHE.put(ExpressionNodeType.END_IF.getType(), emptyFunction);

        register(new IfNullExpressionFunction());

        register(new PowExpressionFunction());

        register(new InExpressionFunction());
        register(new NotInExpressionFunction());
        register(new BetweenExpressionFunction());
        register(new NotBetweenExpressionFunction());

        register(new LikeExpressionFunction());
        register(new NotLikeExpressionFunction());
        register(new LeftLikeExpressionFunction());
        register(new RightLikeExpressionFunction());

        register(new RegexpExpressionFunction());
        register(new NotRegexpExpressionFunction());

        register(new CurrentDateExpressionFunction());
        register(new CurrentTimeExpressionFunction());
        register(new CurrentDateTimeExpressionFunction());

        register(new AddDateExpressionFunction());
        register(new AddTimeExpressionFunction());
        register(new DateDiffExpressionFunction());
        register(new DateFormatExpressionFunction());
        register(new StrToDateExpressionFunction());

    }

    public static void register(ExpressionFunction function) {
        if (function == null) {
            throw new ExpressionException("函数不可以为空");
        }
        String functionName = function.functionName();
        if (functionName == null || functionName.equals("")) {
            throw new ExpressionException("函数名称不可以为空");
        }
        ExpressionFunction old = CACHE.putIfAbsent(functionName, function);
        if (old != null && old != function) {
            throw new ExpressionException("函数名称:" + functionName + "已经存在");
        }
    }

    public static ExpressionFunction getByFunctionName(String functionName) {
        return CACHE.get(functionName);
    }

    public static void main(String[] args) {
        Map<String, String> cache = new ConcurrentHashMap<>();
        String old = cache.putIfAbsent("key", "1");
        System.out.println(old);
        old = cache.putIfAbsent("key", "2");
        System.out.println(old);
    }
}
