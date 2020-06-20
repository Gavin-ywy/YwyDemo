package com.ywy.demo.adaptation;

/**
 * 屏幕适配的主流方式
 */
public class Adaptation {

    /*1.SmallestWidth适配(sw限定符适配)
        实现原理:Android会识别屏幕可用高度和宽度的最小尺寸的dp值(其实就是手机的宽度值),
                然后根据识别到的结果去资源文件中寻找对应限定符的文件夹下的资源文件.
        sw限定符适配 和 宽高限定符适配类似,区别在于,前者有很好的容错机制,
            如果没有value-sw360dp文件夹,系统会向下寻找,比如离360dp最近的只有value-sw350dp,
            那么Android就会选择value-sw350dp文件夹下面的资源文件。
            这个特性就完美的解决了上文提到的宽高限定符的容错问题。
        优点:1.非常稳定,极低概率出现意外
            2.不会有任何性能的损耗
            3.适配范围可自由控制,不会影响其他三方库
        缺点：就是多个dimens文件可能导致apk变大,几百k。
        附件：生成sw文件的工具 (https://github.com/ladingwu/dimens_sw)

    2.今日头条适配方案
        实现原理：修改系统的density值(核心)
            今日头条适配是以设计图的宽或高进行适配的,适配最终是改变系统density实现的。
        过程：adaptation.webp图片
        优点：使用成本低,侵入性低,修改一次项目所有地方都会适配,无性能损耗
        缺点：
            1.只需要修改一次 density,项目中的所有地方都会自动适配,这个看似解放了双手,
                减少了很多操作,但是实际上反应了一个缺点,那就是只能一刀切的将整个项目进行适配,但适配范围是不可控的。
            2.这个方案依赖于设计图尺寸,但是项目中的系统控件、三方库控件、等非我们项目自身设计的控件,
                它们的设计图尺寸并不会和我们项目自身的设
    3.AndroidAutoSize
        AndroidAutoSize 是基于今日头条适配方案,该开源库已经很大程度上解决了今日头条适配方案的两个缺点,
        可以对activity,fragment进行取消适配。也是目前我的项目中所使用的适配方案。
    使用也非常简单只需两步:
            (1)引入：
                implementation 'me.jessyan:autosize:1.1.2'
            (2)在 AndroidManifest 中填写全局设计图尺寸 (单位 dp),如果使用副单位,
                则可以直接填写像素尺寸,不需要再将像素转化为 dp,
                详情请查看 demo-subunits    https://github.com/JessYanCoding/AndroidAutoSize
                <manifest>
                    <application>
                        <meta-data
                            android:name="design_width_in_dp"
                            android:value="360"/>
                        <meta-data
                            android:name="design_height_in_dp"
                            android:value="640"/>
                    </application>
                </manifest>

    链接：https://www.jianshu.com/p/337c47721690*/

}
