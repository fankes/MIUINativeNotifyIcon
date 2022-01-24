/**
 * Copyright (C) 2021. Fankes Studio(qzmmcn@163.com)
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
package com.fankes.miui.notify.hook

import android.graphics.Bitmap
import com.fankes.miui.notify.utils.bitmap
import com.fankes.miui.notify.utils.unbase64

object IconPackParams {

    /**
     * QQ 通知栏小图标
     * @return [Bitmap]
     */
    val qqSmallIcon by lazy {
        ("iVBORw0KGgoAAAANSUhEUgAAAEwAAABMCAYAAADHl1ErAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
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
                "HWRiiAAAAABJRU5ErkJggg==").unbase64.bitmap
    }
}