/**
 * Copyright (C) 2022. Fankes Studio(qzmmcn@163.com)
 *
 * This file is part of MIUINativeNotifyIcon.
 *
 * MIUINativeNotifyIcon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MIUINativeNotifyIcon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * This file is Created by fankes on 2022/1/24.
 */
package com.fankes.miui.notify.params

import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.utils.bitmap

/**
 * 通知栏小图标适配类
 * 国内 APP 不规范的图标将由这里完成其自定义黑白小图标绘制
 */
object IconPackParams {

    /**
     * 通知栏小图标数组
     * 默认使用包名进行匹配 - 若失败则使用 APP 名称进行匹配
     * @return [Array]
     */
    val iconDatas by lazy {
        arrayOf(
            IconDataBean(
                isEnabled = true,
                appName = "手机管家",
                packageName = "com.miui.securitycenter",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAA7CAYAAAA5MNl5AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAXzSURBVGiB1ZrLi1xFFMa/ut13bnfTw4gOBMMkQUJcCAqiZBEQsxFchBAiItGNOzHg\n" +
                        "H+Aqg+jKTVausoi4kCS6EBQF0SGQLCITgviAyJhkHDKLzNvu+zxV9bmY7s7tnkf6UTeJPyhmum51\n" +
                        "1fnqnLr3dNUFHEPyFMnLJJdIRiSlVSKSSyJymeQp1+M6geTpJElukkxImn7K2tpa0mg0bi4vL3/w\n" +
                        "SI1fXl4+HMfxtTiOoyRJDEnT/jtIkUxMlmURyVmShx+aAJKfkrxHUpM0YRh2GdW3AOlua4wxSZJo\n" +
                        "EblH8mwhxs/Ozj4dx/GPaZpGg874oCVHLCI/z83N7RtZAMnDxpjfRURImizLtsxk0UVERBK5SfLV\n" +
                        "YQScIvlPO3wehxJFkSZ5t9lsvtuPgNPt+O+d+fX19YHWQIFFi8gSya67nWoJeDVN00u+7z/led6O\n" +
                        "QrVolP3ywB4ukBUAbyqlLiuSx621lzzPe6ws7JeNjQ09MTHxphKRpXK5/KS1FtZalMv/Sz1riqQA\n" +
                        "8LTWhYqw1mK3sB2Wlt3W01p7AAoZBACiKAKwKUREnPffmvz71nueh3Z4OYTlcvlDAIYkfN932XcX\n" +
                        "iqQBsEWAIw/dVUrtN8Yknuf5URShVqu56HcLHrBVxCho0e3+COB94P6kFCUCaAlpD5YvwyJa4Hke\n" +
                        "jDELSqnv8tcch20Xzhd6EAQAwLW1tQenEg7prBFXtNbBLaXUoXYdyQSAX9QtGMiFlitqtZoF8E6+\n" +
                        "rn03LDy0XJKm6S2l1C/5OqstrLEg2SXKpTDXj3K7urr6dm+lV96cryI94nqN/KmUer63Mr9GenG1\n" +
                        "ZkbuRYtu/2sBvLVDs2WguDQIcBBaogVlv4wwDP+o1+t/btdGKTU1Nze37+DBg7h9+/aW677vj+/f\n" +
                        "v/8KgIlh7XAVWhbAs0qprVb2Ccl5EZkaNh9z4mud6RujiFhcXHwJwN5RkkoXQsz8wvwbo3RQr9e/\n" +
                        "iKJoJFtchNY1pdSRYb88Pz//3NTU1K8YMV0a1SP6+vXrI3lj7969XzqwY2SPdOVUg3Lnzp1nDhw4\n" +
                        "8BcetRBrraRp+p0xJq3X69u2aTabV8bHxz/b7lqWZLN+4L847Ph5FEmN1v7WoOSf1LvENldWVo5M\n" +
                        "Tk525V8k9xlj/i6VSqVhxu4dw8PmM2Ao+lyYamxs7IVt6i+QdCECAKwHQD+wmWNmZmYm0zR9yeH2\n" +
                        "k/YArLvqrV+OHj16KQgCl5n3ugfgt1F6eNBvfK01xsfHO58vXrw4obU+4vL3SBRFv3kAPsLmjkch\n" +
                        "GGPQaDQ6n0+cOPEVcsmqA0FsNpsfeUqpq6urqxvA5uy5JgiCjkemp6drpVLplbwHR921McZs7Nmz\n" +
                        "56oHAL7vnwdQyN6vtbbjkTNnzlwE4HS7sVQqnQdyz484jtcrlcr4zl8ZifcAfA6gYa3tEjKoN0QE\n" +
                        "pVIJnuchSZJGtVp9AsilBpVK5WMRKWytAPgSDrzh+z6s2dzJNMZ83K7veqInSTIbBIGTlKGHCwBO\n" +
                        "AAhG7SiOY1SrVQC4oZR6uV3fm5r4JJcBbJ84PT40AUwqpTrnFL0BKgBOZllmgftnG48D7fOVOI7t\n" +
                        "ysrKybwIYJv0WSn1U5Zl5wCwVqsVtqE2KCIC3/dpjDk3OTn5U+/1HbPeKIoulMvlN0qlUlebIrd0\n" +
                        "tkNrDRFBtVqliHw9Nja27ZbTruk7yW9Jvk6y0+5hC2l7IsuyH4IgOLZTu12tUkodU0rNpGnadTRX\n" +
                        "ZKjFcdxZm1EUtY/rZnYTAfTxE1Mp9RrJ77F5HujC1l0h2TnZqlQqBPC9Uuo1ZwPEcfxJkiSZ6aHA\n" +
                        "VzWyOI4/cSYgD8njxpioSCFhGBpjTBSG4fFCRLRZWFg4RPKeyxdskiTJT8y9hYWFoXdmBobkNzrT\n" +
                        "4kLExsaG4eYLnN88NAF5wjA8TrIxipCWFxokiw2lBzE9PV0jOdOa0UGFiIjMkCzuAH5QSB4muUjS\n" +
                        "RFHUj4hFPsy3SgeF5HSapv/u9O5jo9H4NwzD6UdtZ98kSXI2juMlkmmrLLGo12AB/AciDjB9K6Gy\n" +
                        "5wAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                appName = "QQ",
                packageName = "com.tencent.mobileqq",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAEwAAABMCAYAAADHl1ErAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATZSURBVHic7ZxNjF5TGMd/T6uliBk1pSgtxQwlqo2gKghiU4ZaCOIjRGwIiY8QCRs7\n" +
                        "S4suRJBYkUgXMiIipBYTiZmuKtRHW0biK8xrNE2l9be4d5LpeN/33nue877n3tHf5p259zzPfc4/\n" +
                        "z7kf5wuOUglLeXFJw8BmYD0wApwBrACW5UUOANPAPuAb4HNg0sx29j/aREgakvSUpHGFMyHpBUln\n" +
                        "p65Pz5C0VNJLkmYcQs3nkKRtkoZS1y8qkkYl/RBRqPlMS3oodT2jIOnlHgo1nzdT19eFpHf6KNYs\n" +
                        "O1LXO4hEYs0ynrr+lZD0SkKxZtmeWodSSLoztVJzeC52/aK+uEoaBP6I6TMC55nZt7GcLYrlKOeN\n" +
                        "yP5i8HZMZ9EEk7QRGI3lLyIbJG2N5Sxak1T2ZLoylr/ITJnZWTEcRckwSeuor1gAqyTdHMNRrCb5\n" +
                        "TCQ/veT5GE7cTVLSUuBP4Fh/OD1npZn97HEQI8O20AyxAO71OoghWB2fjJ1w38diNMk9wBqvnz5x\n" +
                        "EDjFzPaHOnBlmLIu5qaIBdmtY7PHgbdJXu60T8ElHmOvYCNO+xQkFWzYaZ+CCzzGXsHOddqnYK2k\n" +
                        "4HoHG+YXPTXUPiHLycY+g/Bk2EnAoMM+FYtxxO0V7ESHfUqODzX0CLbEYZua40INY/e4LniOClYR\n" +
                        "j2CtaFH0n99DDT2CneawTU3w969HsEcdtql5PNQwqHunYb2snQjqfQ3NsNtptlgAD4YYhWZYnYfU\n" +
                        "yhI09FY5wyStpfliQTb0dm1Vo5Am+USATV15sqpBpSaZ91C0aO43ZDtWmNlvZQtXzbC7WVhiATxc\n" +
                        "pXDVDNsBXFMpnPqz18zOKVu4tGDK5sTvCwqp/mwys1LTPKs0yXsCg2kC95UtWCXDdgPnB4VTf/4C\n" +
                        "Bszsn6KCpTIsn860UMWC7EF2U5mCZZvkXeGxNIb7yxQq1SQXeHOcpWVmhYMjhRmWPx0XulgAA2U+\n" +
                        "lco0ySZNZ/JyS1GBMoLdECGQpnB9UYGu97D82/EnHCPFDaTrt2VRhl3G/0ssgKu7nSwSbH3EQJrC\n" +
                        "pd1OFgl2YYfjh4GpoHDqwRTZ9M12dJ0/ViTYRR2OPwK8WmBbZ94j66pqR6ckAYoFW93m2F4ze61M\n" +
                        "VDVmwMzeBT5tc25VN8Miwdrd8Lfkvz+WCKyufJf/tlu0tbibYZFgy+b9/7SZ7cr//qREYHXlQ4D8\n" +
                        "9eGOaF515B4T/1kSLGl3j1fU9oIZZQPRc+sxd8n1TDdNijLsQP47aWa3tTn/eoF9HXnLzP6ee8DM\n" +
                        "HgPez//tOmZR9Ka/DVgH3Dj/Ivn5IeDXSuGmZ8TMvmp3QtIYcMjMbu3Z1VWPHQTKMuatb4y1RieQ\n" +
                        "jVV2fbrUhNVm9r3HgXsGYr7Q6QGvnz7wolcsiLvmezv17TubNLONMRzFFGwR8CX1651tAWvMbDqG\n" +
                        "s2iTgvMhqk3U66P8IHBFLLGgR1v6SZoANvTCdwW+Bi5u9zrk4ZiYziRdBVxHPWYnLgGelfSZmX2Q\n" +
                        "OpgjkLRV0sfpXq8KmVAddrCTNChpLLEYVRhXNoMymOB7mLIX1p3U76lYRAsYDt2/wvOUXEnzxAIY\n" +
                        "wLH02nPT30O2ccYoWVf2mYR/Hh0m23dsP9myltlXk9PJlhkuB052+v8F+AL4CGjWln9N5l/CCEZS\n" +
                        "HWRiiAAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = false,
                appName = "微信",
                packageName = "com.tencent.mm",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAZvSURBVHic7ZtbrF1VFYa/UbDWSktRELl4ioa2iAUURKXyACgXxQsoqKmaqAkqicqL\n" +
                        "JvpgohIjRnwQbdQHYkLRaOKtjQ1VMdVGShu1GppKQ5FWbKlwqOcUaUNty+fDXFs2i7171tyXtbbJ\n" +
                        "/pKTczL3nGOM9e+51ppzzHFgzJgxY8aM+X8lBmVInQ28DDgPWAKcBhwPPL/ochCYBvYA24AtwMMR\n" +
                        "cXBQMQyDvgVSrwbeCFwNnAnMrTj0APA34C5gfUSs6TeWkUGdUL+ibnVw/FW9WX1p09fXM+rL1dvU\n" +
                        "JwcoTJnJQqgXNX29Wag3qVNDFKbMTvWdTV/3jKgL1XU1ClPmO01r0BX1PHV7g+K0WKO+uAkNur7F\n" +
                        "1EuAtTzzmm6arcBlEfFYnU47CqSeBWwkrWNGiS3Asoh4si6Hs8oN6jxgNaMnDsA5wMo6HT5HIOB2\n" +
                        "YFGdQWRyjfrlupw96xZTLwN+U5fzPlkaEVuH7aQ8g74xbIcD5Ft1OPmfQOq7gKV1OB0Ql6iXD9tJ\n" +
                        "+wz6aObYA8AK4BPAj/qMYyPwGeBm4B8Z4z7ep99qqIvVpzMWblPqa0o2buhxEXhHyc4CdXPFsYfU\n" +
                        "hXUI9KnMi/p2Fzv3ZNqZUk/qYOd9GTY+MkxtWrfYlZnjtndpfyjTzlRETHZoz7nN3pTpM4uWQEsy\n" +
                        "x11VblBfAFyaaedU9eIO7TkP39dn+sxH3Z95a6h+ySJnY8oT/bwHG6r3qxcWdo5RP6gezBx/3LC0\n" +
                        "iSIwexy/m5Q2fS3VU63d2ADMI20nOrEP+HPRbzvwCLAXeBzYFRFH+vTfkX4FGjYHSDnr1cDaunfy\n" +
                        "AMfW7bAie4FvAndExI72D9TTSYcDS4FTSJvqY4FDwBRpVm8DdkTEw/0GMooCrQBuiYhdrQb1IuDt\n" +
                        "wDLgImB2BTsH1E3A74E1EbGp54jUp3p8wA6SHaYjpPa4rlV/qR7p0/Zhdb1pO9WTQA/1GUC/rFIX\n" +
                        "tMVzscPLg28ydw+n3jWkYKpwZymWz5u+8WHzNbXawan6yRoC6sTKthhmqz+s2f9vTRnUGQWasP7n\n" +
                        "0Po2//PVe2v23+JP6mndtJkFULwOV1WaboNhD3A9gGma/xR4Q43+2zkf+Jl6TKcP2/NBX60nHgA+\n" +
                        "HBGPFn/fzpA3nBW4EPjBjL3UlTVM6TVt/q6rwV8Oy8ualJP2JwAPAsMsHHhlRGxT5xe+npMPamMa\n" +
                        "mKS3U5Yp0or8zIwxjwOLImK61fCspH1ETAE39BBMVVZFxLbi7xs5uji/Ai6IiMWk59XeDD9rgfMj\n" +
                        "YhHwIWB/xXEnAjfN2Ev97JCm8HsK+3PVXUfp92gxw9pj+lxFH5Pq8aWxt2bE+E91Tmtsp4NDIuIW\n" +
                        "4OsVVa/KJOmbBbiGVKLXjd0R8USp7b6KfvZExL5S218qjgU4mVQtB3QRCCAiPg3cmmF4Jja3XfRM\n" +
                        "Kd6z1fJr/70V/SxRl5Xa3l1xbIs3m7HKzk3od+MLhb1Q76vQf9q0wr9CvTPT17Sp4Ott6k8yx2oq\n" +
                        "LZyoLKd6qfpAD47a+UBh6xR1X5+2hs1+U/lP91usnYhYB1wB9JPR21P8PgmYf7SOI8AcUklzNYEA\n" +
                        "ImIn8Mc+nLZetXOO2ms0mEXxJVYWyLRX6ecUs5X3PtyHjToR8lKurwZe1aH9MGlRt410KnEqMAEs\n" +
                        "AE4AWkcyrYTYPlLV/aiU9nXiCGlVnSXQBR3a1gBfjIg/lD9QX0i6jydIB5Ot09jdxc8rMnzXzRPA\n" +
                        "37NGqCvanvK71Pf36t1UtTrK3GNRVVvpGWRaNLUWW7cB50TE93sVCLi7j7F1cHdEVN/7qWebMm/v\n" +
                        "GIR39Qzzjpfr5nW5F3SiXTJufYj0vYZF6MaG9jgH9v9iPQi0GLifjKVGTbwlIlqb6uaCi4gHSM+z\n" +
                        "UWJ1uzjQ4AwCUJ9HWp2f22QcBY+QSoun2hsbnd4RcQhYTvWM37B4Gri+LA6MwP1fFIO/tcEQBK6N\n" +
                        "iA0z9mwS9XL1sZrfWPvV3LLB5lDPVR+sUaDrmr7mbNR56ndrEOfHVeJp/BlUJiL+HREfIxVM3TtE\n" +
                        "VxurdBo5gVpExC8iYhmp5HgVRfohk53AOuDXpEPKdirlthpdB+VgqsC4klQFexZwBinfNJf0JnoK\n" +
                        "+BcpL7UZ+B2wMSL+U4yfRTpuWs4zG++Fg6hjHEnU49SXqKcXPydbsV5avUrdot447DjHjBkzZsyY\n" +
                        "5vgviFEUXbpK+wsAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                appName = "酷安",
                packageName = "com.coolapk.market",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATESURBVHic7ZpviFVFGMafWcvC/YMa2x/TLFs2My1pIZGiLAUtKohVRIoiKiOEIiIU\n" +
                        "qQ8VgVSyQrEmlF8K+mJYWWIQtaSWmxtYURttBJa1FGurpLabbb8+3LPs9e6dOXPmnGsE8/u0e2fm\n" +
                        "eZ/3veeeOTNzpEgkEolEIpFIJBKJRCKRSCQS8cXkGQzMltQmaZGkuZIuktQgaUjSz5L6JX0pab+k\n" +
                        "XmNMby631T2slrReUpOkjcaYZ4uOkRngDuATsvMdsAZoLsjHyioxHilCO9TQYuCLgMJUchxYD0zJ\n" +
                        "4aXJov0XUFdk3r6GNhZQmEqOAPcG+nnDobul6PxdRhqArhoUp5wuoC2Dp6s8NGfWsi6jRiYCX9W0\n" +
                        "NKdyn6evAx5aO4uogXMWA/ZJWuChc1TSXkm/SjqR6NZLOlfSPEnTM3jqNMascXi6U9LrnlpzajFz\n" +
                        "jhrZ4PEt7QIeBCY7dOqAW4CXgV88r6Qu4IIqWhOAAU8NgI9rVZyWlMC/E3BzBSYBTwMnPJLrB66s\n" +
                        "GP9UhuKMsqy4yowZ2e8I+DUwLaf+dGCrR3IjwO3JGNu0DtAD9FraDhZTlTHzNzmM/Ijj5xQQ6x7g\n" +
                        "H49CtQHPOdrrgZsd7XcX5VnADkegOYUFGos3F+hzxNwE3OZo7yjTOmrpc7gos1McRnxnjpC4jZYv\n" +
                        "5tGk/UOLp2HgjDKdakuPUdYVYXSpRfxv4MLcAdLjb07i/QAsTj572JH0yioatnsRQENeg7blRHcu\n" +
                        "4WweFgGNyd/NjmR3W8Zf5xjzUl5zeyzCT+YSDvfzniPZWY5xtjyc43wM/WQRvSFYNNzLMkeSL1b0\n" +
                        "nVrxf6tj7I48po5ZRFuCRcN81AODFi/9nHpjfh74A9gJzCj7/G1HkeaHGjtuEQy/LMN8vONIbknS\n" +
                        "p5Xxe1NDQHvSPs2hsTfUmG2tdE2B+ad5uMuR2PakzwOUnrJtdCT9nnH0WRhizrbEsK6uiwS41JEQ\n" +
                        "wJmUFqx/pvQD2AbMAr6xtPf5eKrcmjxg6bc0X+revO9oW2uMOWmMGVFp+6TqNF9Gu6QuSSOW9hZg\n" +
                        "eSZ3wHJLtQczCQWAe4nzqWWMz5aMi9+ymnTd2B4vpBLV476Wksj5jrErsM94PqzNanafRWgIaMpd\n" +
                        "jfHxOlMSaPfQmAl0BxZoBDg7i+FVDrG0330mgFdTzHdm1NsSWKQNWYJMBA45xN7KXInxMS4h/aRk\n" +
                        "T6D2avz2mMrJ9usAlqQIdgPzAhN4LDHk4jNgQoh+EmM28HnGImW6WtMWiqN0AK0eWmdR2raw3d/K\n" +
                        "6QUmhRanLGYdftu65VxcqWM99klMHpLkczy8U9K3kr6X1CcJSedJulxSi6TrJfnsY/dIutEYc8yj\n" +
                        "rxeUztpe8ey+zRizIov4ZZR27U4HH1C2CC0S4FrgoKePccdNaeJXA4drVZWETbUoTEUejcCbHl62\n" +
                        "hog3A7trUJhBkmOd0wXwUIqnk3nE11F6vaQINgPnFJh7ljwWUNpXqkpe8RnAC/idjlYyTOlh7oqC\n" +
                        "cs2TRxOwvYrHj8r7Bb+CR+ktsYWSbpU0X1KzpMmSRp9fhiUdkTSg0mt470rqMcb0h8asBcD9kp6Q\n" +
                        "NFVSt6RVxpiB/9ZVJBKJRCKRSCQSiUQikUgkEvl/8i8jWVdXsYky1AAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            )
        )
    }
}