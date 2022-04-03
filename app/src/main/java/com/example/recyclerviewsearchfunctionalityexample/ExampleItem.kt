package com.example.recyclerviewsearchfunctionalityexample

//1. 생성자를 당연히 만들어야 하지! private로 정해놨다는거 이정도..
//2. 외부에서 값을 가져가는 get함수들 만들어 놓고..
//3. 추가 함수 있으면 만들면 되고..

class ExampleItem(private var mImageResource: Int = 0, private var mText1 : String, private var mText2 : String) {
    fun getImageSource() : Int {
        return mImageResource
    }
    fun getText1() : String {
        return mText1
    }
    fun getText2() : String {
        return mText2
    }
    fun changeText1(text : String) {
        mText1 = text
    }
}