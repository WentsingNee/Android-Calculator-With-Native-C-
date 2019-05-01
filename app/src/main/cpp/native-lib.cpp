#include <jni.h>

#include <string>
#include "calculate.hpp"


extern "C" JNIEXPORT void JNICALL
Java_com_wentsingnee_calculator_MainActivity_setExpcalretToTextView(
        JNIEnv *env,
        jobject, jstring expression, jobject tv)
{

    std::string ret = "";

    try {
        std::string exp = env->GetStringUTFChars(expression, JNI_FALSE);
        ret = std::to_string(calculate_expression(exp));
    } catch (const std::invalid_argument &e) {
        ret = e.what();
    } catch (const std::exception & e) {
        using namespace std::string_literals;
        ret = "internal error: "s + e.what() + " exception type: " + typeid(e).name();
    } catch (...) {
        ret = "internal error";
    }

    jclass ev_class = env->GetObjectClass(tv);
    if (ev_class == nullptr) {
        return; //如果方法ID没有找到
    }

    auto set_text_method_id = env->GetMethodID(ev_class, "setText", "(Ljava/lang/CharSequence;)V");
    if (set_text_method_id == nullptr) {
        return; //如果方法ID没有找到
    }

    env->CallVoidMethod(tv, set_text_method_id, env->NewStringUTF(ret.c_str()));
}


