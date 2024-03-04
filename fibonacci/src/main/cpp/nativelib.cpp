#include <jni.h>
#include <math.h>

const double sqrt5 = sqrt(5);

long long fibonacci(int n) {
    return 1 / sqrt5 * (
            pow((1 + sqrt5) / 2, n) -
            pow((1 - sqrt5) / 2, n)
    );
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_shoawcase_nativelib_NativeLib_fibonacci(
        JNIEnv *env,
        jobject /* this */,
        jint n) {
    return fibonacci(n);
}