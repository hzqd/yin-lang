package org.yinwang.yin.ast;


import org.yinwang.yin.Scope;
import org.yinwang.yin._;
import org.yinwang.yin.value.Record;
import org.yinwang.yin.value.Value;

public class Attr extends Node {
    public Node value;
    public Name attr;


    public Attr(Node value, Name attr, String file, int start, int end, int line, int col) {
        super(file, start, end, line, col);
        this.value = value;
        this.attr = attr;
    }


    @Override
    public Value interp(Scope s) {
        Value record = value.interp(s);
        if (record instanceof Record) {
            Value a = ((Record) record).values.get(attr.id);
            if (a != null) {
                return a;
            } else {
                _.abort(attr, "attribute " + attr + " not found in record: " + record);
                return null;
            }
        } else {
            _.abort(attr, "accessing attribute of non-record: " + record);
            return null;
        }
    }


    public String toString() {
        return value + "." + attr;
    }

}