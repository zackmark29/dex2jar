package com.googlecode.dex2jar.ir.expr;

import com.googlecode.d2j.Method;
import com.googlecode.d2j.Proto;
import com.googlecode.dex2jar.ir.LabelAndLocalMapper;
import com.googlecode.dex2jar.ir.Util;

public class InvokePolymorphicExpr extends AbstractInvokeExpr {

    public Proto proto;

    public Method method;

    @Override
    protected void releaseMemory() {
        method = null;
        proto = null;
        super.releaseMemory();
    }

    @Override
    public Proto getProto() {
        return proto;
    }

    public InvokePolymorphicExpr(VT type, Value[] args, Proto proto, Method method) {
        super(type, args);
        this.proto = proto;
        this.method = method;
    }

    @Override
    public Value clone() {
        return new InvokePolymorphicExpr(vt, cloneOps(), proto, method);
    }

    @Override
    public Value clone(LabelAndLocalMapper mapper) {
        return new InvokePolymorphicExpr(vt, cloneOps(mapper), proto, method);
    }

    @Override
    public String toString0() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append(ops[i++]).append('.').append(this.method.getName());
        String[] argTypes = getProto().getParameterTypes();
        sb.append('(');
        int j = 0;
        boolean first = true;
        for (; i < ops.length; i++) {
            if (first) {
                first = false;
            } else {
                sb.append(',');
            }
            sb.append("(").append(Util.toShortClassName(argTypes[j++])).append(")").append(ops[i]);
        }
        sb.append(')');

        return sb.toString();
    }

}
