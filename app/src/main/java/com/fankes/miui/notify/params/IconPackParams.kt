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
                isEnabled = false,
                isEnabledAll = false,
                appName = "Android 系统",
                packageName = "android",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAD4AAAA+CAYAAABzwahEAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMCSURBVGiB7ZlLixRXGIafMzqC6MyoUfCCEFwoBpzZGK9hQGzQlW4SENGN4FZI9Ed4\n" +
                        "+Q8iuBdNQEVHhZCoW10oLt0JipfRqIwyj4vuoduxu27d1V2R80Bvur/z1vueS5061RCJRCKRSCQS\n" +
                        "iUQiFSKkFaiTwEHgHXA+hPC0dFcFUH8EjgFLgCshhL+7ETuuztpkWq31ymyvUGsNb3PMqseLig2p\n" +
                        "r/2W91UK3wj9vo3PV+pQEcGVbcQqFT4h9Bw/FBV+UtXwGUI/TmqfeHNTdwFTwOIOJR+AAyGEqQ7t\n" +
                        "lwC/ABPAT8BmYCUwBow0yt4Cb4AXwKPG5yHwTwjhvw66NeDPFF+1EMLdpHyJZOjZr0ZeXaOeUm+p\n" +
                        "HxPapfFRnVJPqquL+umKjBc7oV5SP3URthMzDe0TGXxkCp26j7eEnwSuUt8nq0jisusKdVJ9V8KI\n" +
                        "dkv5N1rr035mwEFbmbFA6FwbvLoAqAHDeS9UIsPAb2o5ntTl6s2Bjm0yN9RlWfNkurlZfwK6A2wp\n" +
                        "2nF94gGwJ4TwKq0wdaqro8A1qh8a6g9K19WRtMLE4Ooi4C/g5x4Z6wfbgCumrPm0ET8HTPbMUv/Y\n" +
                        "A5xOKui4xtUDwOWkmooj8GsI4VK7H9uGUtdSPyyMZbzII+B6Q28/9cNILymq/xrYHEJ4lqlavZhj\n" +
                        "GzmjLmxpO6ye7eE21a3+hayhd/v166YkbqnfzBo1qLfzpCtRf1bdmSX4vRzGDiXoHM6XsVT9f+e3\n" +
                        "H5onthvYkdo7TZIeFF7m0Clbf5fzRn3+dvZHDjGo75md2J5Tq2z939t+q65XP+ecitPqhjZaG9S3\n" +
                        "ObXK1v+krpvTaB3xg8CCnL04AtxTj6ij6ph6FLgPLM2pVbb+QuoZgZZ9XJ0C9vbAbJW5EULYB43g\n" +
                        "6hjwnGqds8tgBlgVQpiem+rb+P5DAywCtkJzjY8PzkvfGYdm8IkBGuk3E9AMvnGARvrNJmgGL/bn\n" +
                        "2v+TFdAMnvX4+T2wDJrBRwdopN9kfhMbiUQikUgkEolEIpGy+QJozXWVnxEugwAAAABJRU5ErkJg\n" +
                        "gg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = true,
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
                isEnabledAll = false,
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
                isEnabledAll = false,
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
                isEnabledAll = false,
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
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "支付宝",
                packageName = "com.eg.android.AlipayGphone",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAK8SURBVHic7ZpBitVAEIb/9hpxIcgIeg15C0HceAxlQLzHu4mLDAMD48JbKGQQXOg5\n" +
                        "fhdJIC8vnepOV3fnPepbTvKqqv+urq7uDGAYhmEYhmEYhnFdkDyQ7LhC7RirIomzB4FcTechAjjn\n" +
                        "qsb4rKbzS8AEEjCBBEwggaQCuIddJoSUQm8ZJGACCVgfJGAZJGACCZhAAiaQgAkkoLJDkGwA/NWw\n" +
                        "pcSTc+6VhqFrzaB7LUPXKtBD7QCqQbIpeQt5iRn0Rnh+VySKvUKyFRLoUDvGagQsr07b56UtsY/C\n" +
                        "c7XdayT1wqxBXxNeA3gx/Pnl5JXfAP4A+AXgp3PuX6K/DsDNyivPU30kM6T5kQHftDy0w++jagX7\n" +
                        "j4yrdnONOTTAhnKB3EIXIhjlCalXnEneZhDGRzv4ayb+pexRL865xNm67HyMYlXLntUiPTh+XHj0\n" +
                        "hH7HeICn+PK0gL8F8CE52mXUDqbRLMxclzJb7JdKSoEvmj3SYOZLS3WXmIiVSsdZzSoCT2c52xbK\n" +
                        "fncMqTMhtCyRUTxv6YvMjqJQecXi6bZ6zOLE77tRFIkM7LNig5zWn6JFkHl7Lh2xaglEuSEsLtZu\n" +
                        "TvP091xT7tD3YBrcAPgC4DE6s0pnUGDmdLP3c5wLR86OO2sBZz0lM1CcpWCZXyj//fbsvSzbPBPE\n" +
                        "mdkZr2DUWXM6dah+Wg4ckCjOzGbqXVWUQPNmsY0JdsWu+N/1W8RZ8KPSdEpO5rPckbzdGHBMvVCZ\n" +
                        "jA1+4wQaHCzNQugt4IHxM5mlc98qlHhpz34mf2D9snypN1l732fjk3Pue+TvohjG8xXAe8TH6Dca\n" +
                        "mQWxFD3vTcYlZnesQe2t9MjS9zjL4/Iuvy3GUnuOzYU+N0tjS/1weADwDv16Bs7X9Fib7tF/QPxW\n" +
                        "/cNeIMMkfs5huNnDsjEMwzAMwzAMoyb/AfL3c7cVoO+XAAAAAElFTkSuQmCC").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "淘宝",
                packageName = "com.taobao.taobao",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAE8AAAA8CAYAAAAngufpAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAzFSURBVHicxVxfjF5FFf/Nbj92W8oqbCihGlbKH7MmlodiVVwNNCISk5o0MX3goaiN\n" +
                        "qaKxPOADCUpEYgIxfSAhmmKKphj+VKUQMfDQvhCIFrUQKxSlBc02YSsWdxu76+7Hz4dzzt7zzTcz\n" +
                        "997tH09yM9+dOzPnz5yZOXPmzBdwDoDkHgBXAngBwFEAhwG8CWAyhPDOuaDhbECoK0ByUH8OaPpe\n" +
                        "CKHbpF4IoUtyBMDfAFycKPYugGP6/QiA+0MIx6xuDT0Gi3Ql8uJ8NKH9tIHkYIJQ/71TV1/TCQos\n" +
                        "kJzVdIH9MKOCTtHRqcPXBqzN021nWa5x6yGSGwB8HsBqAKcA/A7A4yGE6ZKGQHq/C+AzLm8oUW5O\n" +
                        "83+kbXYg2jIQQpjX9hdxkFwNYA2AKwBcBuBSABcBGHH8TAOYgWj2cQCTAN4AcCSEcMy3afjOiEY6\n" +
                        "jRkhuTehISQ5SfImX77QzvNO83IwS3Is1QbJtSS3kdxN8qBq6FJhRtvYSXITnaZTtDs70toIsEPy\n" +
                        "gGPaD7dZR4wJsBPVN8GNNxAaST4QCWwDyQdIHirUXWj5pGBKBbnW875koWl6b8RcjulJksOFdu50\n" +
                        "jKaYJ8lJLTumeA9n8JWE0Aa8MnjYRXKV7/ylCHCYIhQmEMQMkeTmHEJWmlNq506SDyYYzAnrTGic\n" +
                        "b8sryAmSG1sLkNVQW1uD0MCQ7oi0zdKbHIE5mGHvHBZrhDGX0pQ20ESQXoh3NBFgarXtG4Y1sFLT\n" +
                        "96L0Gy3q2opr9HQBDLoHrtw0ZAWdQrWqngIwq2WX63MJgDHIiuzb6EbvBkOoVvX7SCKEcD8LFkVK\n" +
                        "eG87ZprAUU0HFGGX5DoAGzW/Tv27SkfM1ByA1wD8CcBLAF4F8A8Ab4cQppsQRhkFHwJwHYBNAL6g\n" +
                        "OEwYMW32rQsR4BshhF+VBOiR2dDdn1Dl1FAgdaWiM6pJPhqVaTqUDrMyI/pMlxS9rIzoRWM6N9xI\n" +
                        "XkUxeWIecrzNUOzK+jnQMb/eNRQL0E+yDzombK5bVxBYiuBJilkywYSZEAmn9SroBDzo8jY5HnIC\n" +
                        "tO97GglPC5kQttQIYW9G8Ka1MVHx+37qyhYxOqzP6Rus/bz5Tl7fQICWP+FlU4tE03WUXcYJVhp3\n" +
                        "kOS2qHws8HjF9O97HDFjlNV99AzLqY6/YU03NhTeXi+XJgg67vcoZbeQ3EJpukqF7JH6If8MyfWu\n" +
                        "np8aJik7mt0k79JOWHWaMkrx5IeudfiuBK0pGI/bqEWWKhznUYaZH66+Jw9Rt3GecJYN6CnK3rpn\n" +
                        "voueTvT0zW1NBEnR/pLg7NvdrYRXJ0hHQG6BuSuqb8Ml7m2/ZTrBBittC5o7lM4doYyOHmeApiXr\n" +
                        "wPIOxO3XOkMNSQhhPkeg2nYTAH4LMXxfAvDVEMIrVgbqYiK5HcAO9Nt19v4UgJ9BXE0A0HHPee73\n" +
                        "cohBv0zfL9A8+z4EYIV+N2N8NYDvhhDuiWjaDODRBE0eugDGQwh/NZ7rhDbsfmeHBHvNlHsT+ZZu\n" +
                        "KgyPcwVbjSY2H7qmfVtMFiWheWP3EHV19ILICTDRhgluIkFMidg2z2yD7yacvomfohjmzUnR1uM6\n" +
                        "ywrPMbs5amAPyatKQmS/Meo1MrapmgijCbTxpOxOCU7TZyL6YhwkuT8ptKihUVZmh2dkgbrq5ASY\n" +
                        "EFzKGK0zC84kzFJMoR1M+x6N550F2ozuI76N5BkGgJ8AeD96HQT2+3skvwTgyyGE3xtyP4FSJtR5\n" +
                        "kpsA/FKzuy4dgpyavQ6Z3G0xmgewAPGWXATgxgx9NrH/BcB9mjcL8a7MaTsnNe/fEGfCbKYtg79r\n" +
                        "mpKJaepqiLfmLfqh63pgW6EH4vw74vpRW5sy9XazZldBMcpz0GP5NwHm52qjdWvUdg7Wx/wuNsTK\n" +
                        "2LWGSnMAqfNIQoA2bDe6sj0GsyO8x/jVPG875vA/o2WHmTGY+5jMCJVVR9dt1czT3OlRUR16N2iB\n" +
                        "2wFcr59i/5elcwBuIXlBCOGLfujqsO2EEJ4ieSuAy0II93ihWfloyNvPNkeBXcVXOskbiLLj48Z3\n" +
                        "G+K60OoXS1FOseo0MXbHJ3ch9lu1JLXdssd2IY0173SAlfZPFPB5nNutXu7Q2w6C9wHYRxlqPwBw\n" +
                        "rRbxlri5r7eTfDqEsI9uRxJpY88Bdgbs+4kWArAtl99xnKe/V0B2Hucrrcsh2vOC7oDscP5UQ3Qr\n" +
                        "NB1ICs8Yd0J8FsCzFFfUDyErsRfggv7+NoB9cCpN2af6VXOZlvXbrkFUZydG3JoCA4b30yQPQ7Zf\n" +
                        "1tYyl5bmu9sBvKJl5lGt+DkwHs/3jGTBCXEwhNANIfyY5BMA9kDmQxOgtfNR0zqnfdcD2FlD2FJh\n" +
                        "JYCrG5Tz2r4A0cBXEt9aQVF4Bi5upaMhYTeQPATgIxFyGxa+F/0JWSN8Dpq4f9owb4I7DnFeAHUT\n" +
                        "fz8smjzxClQE1SgbXk86ggxOon/ueF/0vpB5lgqDLR4z+L/lgooMmh65LipGW00Aqp42n5tv462E\n" +
                        "yfBBTY3wM3k20YUIft6l86h2Gafc+38gQZUPhxD+wGoXZPScV4PL+JyNMxqBQzgKOQMFRBhzmh50\n" +
                        "xW04/BHAYwD+pe9zmhqjs/rYexey0l4BYNG9FYHNtc8BuBUyVZzS/HkA/y1tx2wO11dbbW0hKPn0\n" +
                        "gEp47YY7K2t8R2TjmQ2UjJpaClDOWOtsrkdbtJfccTg7b3PUdg7nNqvXWPPcKroOwHbNNhtvELLR\n" +
                        "fxGoVmmrh/6eLE3yg6o1FxbK9NEG1YQaD+9AziOOynNdB2Z/pu28FDikP9U0Vu/9qWhRrVd04Ud5\n" +
                        "bYaDLTTFyE5nNqXK2LC1mGmz53Jw3HA2Ep7TursBXIN0LMvjUR0729gMYBxiV01BYmHeDiFMn83g\n" +
                        "arozCrcwfB/A0RDCQ4mOqzt0MoH+sy0Rub3fYoAiE8HYWi+O7jTn5EGK93ZVhCd1IpfDu8vXsd/R\n" +
                        "+zDJ2yiOTJK8L66j76Xw32TsSlHznPYMA/i5Zqe2ZXv9kHXpaogDEai0dUjzVkN6exZIzlVmOpRW\n" +
                        "Pxu2A1RvjDPoRwF8BcDXAVzu6iy3OqiCuoejMjk4DrUaQgjdumFrCB7SxuPhavV/kam3BtU+2Nez\n" +
                        "dl5MzJOLTDWFaIEaB/A1ALegmsfMHhyCW4gc3sshHmKgPN8d8yZQVnis5rmtSkgsANOIl0MIzysT\n" +
                        "xrRN+h8oEAJIiD+QFljJaDWNP6m0jgL4LIAtAG525WxL6AViYRx+YRpHFZtXEt6riq8TQpgv3cOY\n" +
                        "p8Te5Tb1xsAjvsGoTN3yf7LwrWQrGt1rdA67Fb03jKwjUgGaKwBYEGZHy36ihk7j1W8C+jUvmuds\n" +
                        "/5rqkSFIzz6i7ykTY0Uiz+P9sNVl5e01F1EJjBYfohZrbk6DVrrfRvN1NfisEyzk4j2g7Bh4DNU8\n" +
                        "lzNyn6y5K5YTgrV3M8kt6u7qqllhc0qTsN6u0me4TUMGIW71uUSdEdU4uEXtmoiuGAcgm4DXrB4Q\n" +
                        "aZ6b5+6C9GpdbPIuTXOT/FShrsHDJL8J4M+Q/ekIZK40P11pDhqMfpvQ7gfwa8gty5j+dyJ/440Q\n" +
                        "bayb71KLmwD7T7vq9niHclhY2Wrrojql9tpAKqpgknKnY1Rx+/skPtziNvuu6XM1dJTjVFgxO87m\n" +
                        "sbp2V6Hu9uOBqF6OwFTMSa6chwOUYMg4KMnHABqYUW2KUjob9nCCVackD7tHWFnhddpQGyXuCNzg\n" +
                        "6rUNs8gJ8gQlxm8ixsne3cVBLf88VXMi2iwyvk5RdpZ4BZtdH7Bvuz0RBa3LBYfnIptKt31mKUNs\n" +
                        "K6OQ2xxTlHjnq6I8G67WqU2mjXxILctBLjHTpNzBzfdEgjHK/PdctuU0riOUMLetjKJF2fKKp5Y3\n" +
                        "wa1i/f26WOv6FCVQjMw7kLbGPdhq9FII4WNNiTbE7iRuHMCnIObBpahswWnIvtH+g+BVAG9GW69F\n" +
                        "2uLAIoduAJX9tmg3uj3vGMQDfTXyK6zlvwvgyhDCO8yssltYTerWEyXPgnlSW3mL2SLYOlVvKXWj\n" +
                        "dkZIbmd1UbC0usZxKUncwTUex6cA/T1zHNITje5+ZZiwnUTO6Vn7RxCsdkFjkDPktyDaehRiW9q2\n" +
                        "byXEOXEtgM+h11FQMogHAXyn7uJeyq+1gVWUZDz+k/Eo5xqYXsmbQGlUtb4y2kMQe5f59axC7A0W\n" +
                        "L+mdBZk0BlaL0Hala4a9K3XuSQnSC22KEpC5NB4TQhynxCM3v8B2lsEJ74EGWtYkZnmGciLYbwgX\n" +
                        "IHsPg+4MQN+zdzH+X0Bx/a8F8EkAH4f45S5Dr+ckB8cAvAzgNwCeCCFMaZv1dywUGv+jz9k8rDlT\n" +
                        "oLReAjGBxiAH5xdDXO8LEFPodVRm0HRUtxWf/wMVHEsUTW3uUgAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米穿戴",
                packageName = "com.xiaomi.wearable",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAYAAABXuSs3AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAI4SURBVGiB7Zq9ahtBFEa/WVKmNshBpZ/AgUDAMcTunSpu3Cov4E5voFcQ+AECLpw+\n" +
                        "NiKG4EBcGgKp4zIuJFBlnRQ7Eip2du7+WOuBHFgQo733nh1mZ3ZHkjyAAwbADTDl+TAldxoATusA\n" +
                        "PeCyUz0bl0BPkhz5VXyV9F5pcCXp0AEDSeOubSryyQE3kt50bVKRHw6YSnrZtUlFZg6ga4s6ZF0L\n" +
                        "1OVFS3kWkr5LupB0K+neH5K07Y9dSR8kvVUbHdZwXp0DI2CrQr0tHzNvUriJ+DnQb9BhfZ9jY+IL\n" +
                        "YFhXuOAChj7nk4ovgOO2pNfkj6vKVxVvracL5IdPJX5uKH4AjIFf5E91U/95DBwY4s1j3io+p+RG\n" +
                        "BHaAiSHPBNgpydPHONtYxUclxfaAv8Y8+HP3SvKN2hJ/JDBPk/d0Fel1+cKeJ5/nH9sQvy7pHcvw\n" +
                        "CDEpyXsdC7YsvReB5IeS9g3xIfYJ37BfYsEW8dtA+0dDbIzQmvAzFmgRvw+0vzPExgjlCNVc0UT8\n" +
                        "lSE2RihHK+IufkrrRGtaxHuB9j/VXCrlCNVcYRHfDrR/M8TGCOUI1VxhEd8NtH82xMYI5XgdjUx1\n" +
                        "AUp2ybcMlUzSadEXzrnfko4kPRjyLHmQdORjiziVZQgbeyjZx1pI9EViSZKvbpDwy/JSPrntiXWS\n" +
                        "2xBap7MtuLa2mTe+6fl/f3zTZJJmXUvUYJZJuuvaogZ3maSzri1qcJbsD7SZcw5JJ77huXMl6cQ7\n" +
                        "55DYnxD+AevUWpo12Yg8AAAAAElFTkSuQmCC").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "微博",
                packageName = "com.sina.weibo",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADgAAAAyCAYAAAAJHRh4AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAARUSURBVGiB7VrtcRMxEH3K8IdfBBrgmIGfzFwHmApwB7gD3EHcAe4gTgUJFdipIE4F\n" +
                        "MRXkqODxY3WxLGulvQ8wMLwZz/jGJ2mfdm/3ac8OfxBIngP4DKAGUAHYBD9vnHO3JzFsDJA8J3nH\n" +
                        "Mu5IfvGb8feA5MxALsYlyerUtptActqDIEk+kvxyavtNILnoSZKU0D0KW3cKIiX4sGtDb+I/HwxD\n" +
                        "twA+OueaX2XbLwMlCc1J7gqeXJ/a1sEwhPKsvXfUECVZQ0KpgtSyHaSWfbOGjQ/PC3+5g4RdE9dA\n" +
                        "v9YGwIvENA2AN6OEqg+dzyQfxggbkjfKHA8kJ9G9Ncmm5MUh5C4oadqC+UCCLa4ZZEuSE+W+uyHE\n" +
                        "Jsx7LIVNeeaniFh4olpCWURjlsp9r/qQu+hIrBNBhXDKq1VwT6Ws+bbrQhatqGHVh2Cw/jyabx39\n" +
                        "vkkRfGacvAawBjBE4CY96D3x2l9WkMx5H2dA59yS5BT7gn+QcCCZM8a7olWU582aSHKYBHNWlFDP\n" +
                        "PceXjKQXJWuGCMM0VRvfl8j1Ufg5g7+yW5gfZcLo93DT4mzakHz+u8gNwTSyaxv8Nk/YvKM8jzUA\n" +
                        "nCnkJgAus+79faij60b5DufcyjlXOecmzrktkCDomV+PbmZ/3ETXYTYulp6DLEp5qIdmyzFx33qi\n" +
                        "hXNuRRIQfborTRCXiWv8OeSAY+8BEJLWCZ4IUuRPXFss+A5R/FtIyOxSO+ujow4+U6RPAiHG2Wzq\n" +
                        "UkdDQ9F/cQLouu6UaQUS4prRKaILnF9oBelHlvADwBLAMnXW4v48GO58sZ/p0/0CeY9u/bpXBjsP\n" +
                        "Jrd6b8NEi46iUUuq5NHfo4Yc82e7EMnmUo6gdtQIkXyo2V3GPTIT1jyWYirJLgRL0kkj11fplEha\n" +
                        "W4e2E3thkq0ypm+TtoXawqCEvCVUbefLwiTJnWb303wKasudtseGFn5JLerxLVYRfvEZ9k3ZIcil\n" +
                        "/mTk9EGOoKYWptH1FaRF5wC89NcW5DapKMEgJauIHEEtxsMUfeucm7XKxdfGOUTdDIGlDJi8fAbF\n" +
                        "GGPTdKmMsySAnJcsCimpU2OcacZQL6bqeSyAxQM5Az8NHP8ElSD0JBDef0TEb0zpTdCVFiE+c5c8\n" +
                        "eGU5KoWTphqsWoGvuK9TB7KJUsPWhezeZKIDxvHdsjj1wp2cKLr/0Ru1pk22xVk4nNeijvq9c2C6\n" +
                        "e5xTHDPaFEeLJmccbTp0oY23EDznYceqhdp8ooRr6WUJqZxEgnkson1QZzwkmXoej5qw0bia0lq/\n" +
                        "8WQ2/vs8R8yPtbzrGP4qLCKZ8uQDM89Pj3VK7xTpN7v3ab5kgCZ4Hygd6s56lOQHP9aSjFYc4c8+\n" +
                        "2VfYlJq0hF7XGuybTcBhjayij9UTtwBmnercUFCSgCWZ9EXjPTb6v5Y6/QnBGzCFeMMip3K4h3h8\n" +
                        "45wzya4+GPQvC0oCqCGSLReCbSjvIH3TXm97/+M//kH8BEhmToz+KexyAAAAAElFTkSuQmCC").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "抖音",
                packageName = "com.ss.android.ugc.aweme",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAALtSURBVGiB7Znfa41xHMdfHynkxwVN0UhYbGkNhRpFcaFYbtzaDSK2/AGKC5e4oEZZ\n" +
                        "SSkuXLASU2q1C7VWcrNxoQwrbNiYm928XZyz9Th7znm+3+fHOc/K6/Kc7+fzfb/O0/f7PN/zQJVQ\n" +
                        "NNeT9F+QVtAUqE9SnCeRuiTFllaKKCTJYVi9mY3G6Z+nKwJwrNYBInFY7JI0WOuckTiKSNLpOP3z\n" +
                        "tkYAfgMNZvbVp3/e1gjAcuCFb1EeRQCaJY1Iaqt1kDl4rJFSbktaH9U/j2skjB/AM6AbGAXGzGwi\n" +
                        "OGC+iJTSZ2YHgh/kdY14818kb+RJ5H2S4jyJ3Ae64hYvTDKzpN1AG4WzRB2wGBgBxs3som8/Mzsn\n" +
                        "qQc4g+eTsLeIpJVAB3AU2FlmWJ9v3xnMrBfolbQPaAcOA2uj6rxEJHUAncDmOCF9MLN+oL847y6g\n" +
                        "FdgIrAFWlY53Eik+ItwD9qeW1AMzGwAGKo2JFJG0l+Ivk2cq7lqSljAPJCB6+71RlRQpUFZE0lng\n" +
                        "ZBWzJCJURNIKClvsvKHcYu8AGh17vAEeUzgvDFG4t5S7v1QXSUOOp7dbxavn0jOKy0kyz7kikrbg\n" +
                        "djVOmVl3ksnTJGyNtDrUvcyTBISLbHWoy922HCYS9a/4L+B5BlkSESayOqLmlZlNZxEmCXEOVsO+\n" +
                        "BZKWOQwbj5FlljCR7xE1cZ6Amx3GvIvRd5YwkahfpqW4RfvgIvLWs+c/hIl8cKg74TlPe8T3P83s\n" +
                        "k2fPykhqcbgLT0na5tjvjkO/h6lKBCb/6DD5U0kVj7ySjjj0kaTOrES6HANMSeqQtKmkfrukq449\n" +
                        "piXtyEqkUdKkY5AZPksalPTHs+5aJhIBmSuegeLwRdKGrEUWSXqdsciFTCUCMg0ZSjyqikRAZk8G\n" +
                        "Eg+qKhGQaZI0nJLEpZpIlMjcTSAwIul8TSWCSDooqcdDYELSTUnrsswV+2WopCbgOHCIwhmmDlgK\n" +
                        "fAPGKLy4eQL0mNlk8qiV+Qt8xU94PoUr+AAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "哔哩哔哩",
                packageName = "tv.danmaku.bili",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAALMSURBVGiB7Zm9axRBGMafV4KIUZCgBFTEkKgg+FUFtMk/YNDCRhEVg5ViIdgoJBZq\n" +
                        "kc5SBAuNgqXa+FFYiEUawRQSOY0SQhBF8IO0P4vbwN3ezN7czu4lxf7gipt9n3eed3dndmdWqqio\n" +
                        "qHABjAMLwCxwu8C8t4EaMAeMF5XX19kErUwBvRE5e5McaSaK9J7udM7RIcBrYCBHvoFE62KhjBqW\n" +
                        "O571dAowAwx3kGs40fh4VWYhkxkdA3wHRgPyjCaxWZwrrZDExJU2BgDGMvRjAfrrnfqyQPO7JW1t\n" +
                        "aNoi6Ukb2TVJ71JthyXdbKObknSv4f+8mX0O8ekFuAXMB5zBspkD7mR59V4RoCZpMOpMFE/NzHa5\n" +
                        "DqxxNQLvtfqKkKQh4K3rQEshwCVJB0u3lJ8jQMs4c12RU10wE8vJdEPTGAE2SPrrEf+TdEHSYvG+\n" +
                        "WuiTtE9S1mvKDjObdx4BBn3TRhluQ8iYyUYa49K3Vr8n341ybAYR9PKYLmStJ246zksUn0KCnNOv\n" +
                        "g6UII7EEjcnQQloANlFfDC0m9+zTDvUjwP2Ge7649UeSvO3ASmKPOeKCzeBepJ3I6yn3FZF02dF2\n" +
                        "vgO9azl7OqeXqEJcbI/Ub8wrLLqQFaMqRFLN0fY1Ip8k/cgrjCnEtcvxpgN9evUoSV9yemmmk+k3\n" +
                        "iX/cEPMC2NlBX3uB6UT7B5gswlNuEbAN2BNagEO/H1gX66knr4FlzCxqI83MPsR6kKpZa/URWshK\n" +
                        "ruGHQoJCCzkTYSQW32vPL68COOCZIaC+ax50dooA6KP+LcZH02o2vfmwWdlP1yVJs5J+F2+9Bf9z\n" +
                        "QpKZNXnvSR38CTyTdNSjXy/pUJS9YniQbnCNkbtdMBLLw3RDSyFm9lzSxa7YycdVM3sZHE19KVrL\n" +
                        "GGzd5htw1ue37fcR4Ljq3zX6k59vy6gsPkqakfTIzLoxyVRUVFR0gf/L83vOF7040QAAAABJRU5E\n" +
                        "rkJggg==").bitmap,
                contributorName = "fankes"
            ),
            // TODO 可继续在这里添加更多 APP
        )
    }
}