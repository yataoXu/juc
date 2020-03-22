package com.evan.core.proxy;

//定义了一个接口
interface Hello {
    public String getInfos1();
    public String getInfos2();
    public void setInfo(String infos1, String infos2);
    public void display();
}
//定义它的实现类
class HelloImplements implements Hello {

    private volatile String infos1;

    private volatile String infos2;

    public String getInfos1() {
        return infos1;
    }

    public String getInfos2() {
        return infos2;
    }

    public void setInfo(String infos1, String infos2) {
        this.infos1 = infos1;
        this.infos2 = infos2;
    }

    public void display() {
        System.out.println("\t\t" + infos1 + "\t" + infos2);
    }
}