/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version.
 * <p>
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * and eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 *
 * This file is Created by fankes on 2022/1/24.
 */
package com.fankes.miui.notify.params

import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.utils.bitmap

/**
 * 通知栏小图标适配类
 * 国内 APP 不规范的图标将由这里完成其自定义单色小图标绘制
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
                iconColor = 0xff72c476.toInt(),
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
                iconColor = 0xff4981ec.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米SIM卡激活服务",
                packageName = "com.xiaomi.simactivate.service",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAHpSURBVGiB7Zg/TgJBFId/z9haegITC0KMCZ2Frr0tBYXxKMAZCAcwseISUNGtwcRw\n" +
                        "AS9gY/2zcI2blRlm3sz+hS8hkMy8N+9bZndnBnCA5APJOck147JwGT8KJF8iF1+9DMnHkiXKlyF5\n" +
                        "VZFEFJkTS9ttSGIFwxAZm8i1NmkAahmbyIWymFBUMjaROvGWaaoI4CnTZBHAQ6bpIoCjTBtEAAeZ\n" +
                        "togAe2TaJAIA56aGtokYOYo0jc6InAbEfgHYZt+/JLnfK0tsYugzAHAWUNN/SC4te4d3kr1C/yTf\n" +
                        "wTFvUmjrZblNLE15NVMrFZG+iGwVsVZEZCsifQCpb6xG5E0RU/oYGpEPRUzpY3TmqdUZETE1ZE+I\n" +
                        "xNReEysRud/V0Jl/pDMimjf7FPa3dgwSAGOfANUSRURKFSm+8V3ozNQ6ijSNzoiobnaSk9iFhKIR\n" +
                        "8XosVsVBT62ppS3/b5n63eFvDeeaS49lq7vz/oix1c31mVSx1W0kBy1yGb2KCGNoREYkh4o4J7Lc\n" +
                        "I9847QHdguQUwAbAp6EgpxVsoZ/38t1lgLnloKwu5qZ6bVNrE/XKxMFYk03ktYRCQjHWZBQRkRTA\n" +
                        "rJRydMyymnSQHNd9Y9BhtW081yrI3AB4ws+x/0B9VfxIs8+ziKz3df4G2ybTnqI5trQAAAAASUVO\n" +
                        "RK5CYII=").bitmap,
                iconColor = 0xfff49026.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "音质音效",
                packageName = "com.miui.misound",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMeSURBVGiB7Zg9bE5RHMaf4yMddChNidZSNVBvozSxlKRpJ2IQSYMQMVikaVh8bK2B\n" +
                        "xCISQ0mMRCIGMRCDdECEBB0QTQhKoww0UaHTz/De8jrv/3613t5K7m+7//Occ57nnnvvufdKOTk5\n" +
                        "OTn/G0AV0AR0AFVZ+5kWQDPwjD/8AK4BtVl7SwywhGjas/aYCOBhTJBJYHnWPiMBjsWEmOJ61l5D\n" +
                        "AWqB94bpmyFhVs2Wt3kp9fskrfBqbyQdlXTF0G+ZjqmKAwwZZ/1Q0HbAWqmsPZcBFEIun0LQ3mi0\n" +
                        "fczadxnATsPoA0/zxNA0z4a/BSm0G4zaPe94SNJ6r1aQ9CJq4CBsh6Qfkkacc3dS+EoHMGic7a2e\n" +
                        "5qChOR0z7oDR53glg4x5k00YmhbD1GDEmCcN/RRtlQhhvZLcDtFOerrnIboCMBER5EIaj0n3kTVG\n" +
                        "LeyJ9Mk7Xhai2ytpUcScXXGmSkkaZLVRGwvR+kFqgfmGbmXMnE1AfayzgNlYEUlaatTigkhSSwKN\n" +
                        "pMoEsVZqzgSxljjppSVJDaUHwFJJiz3NCaPfunhrRZIGqTNqaVbEX9FGQ/PaqLVGmSolaRDr0phJ\n" +
                        "kGFDYz3BCoB1EsuIDULxS2+hV/7gnCvbEANigzjnxiVd8mrnJb00+nbEeUwE0G1sVlcj9KsM/ZcQ\n" +
                        "7XZgW3CyBJyK2CAtbgD7JcmVDFonaW3JPDWSOiX1Gh76nXPWzSmgWtI3o6neORf5Wg+0ShoM5k7K\n" +
                        "uKT2qQEGKP7OScIoMT8WgHdGv05P0wBsNPoeTrkqAH0CWlN3igG4bPTrKWk/U1I/Z/TvS+HnK9As\n" +
                        "YHeKTmfjQgRGeo2+F4O2NqNthzHGLuAu8DnCz+97REAN8DRBiB5/soggm43+j4zVmOJI0rHDcMHg\n" +
                        "HZK6JZV+lv6U9FbFr7tbzrlXKYJUSxrR37v3d+dcNTCm8jfiTc65+9PwX3ko/gf26TdqiU9QJmB/\n" +
                        "9lokuu8yg+JX5WiCILP2N3LaAHtiQlib7dwE6AKGvQDDFB8w/wwXL5k5QI2KL39Nkh5Leuycs15j\n" +
                        "cnJycnJyMuEXxX+pSnClwogAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff82529d.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = true,
                appName = "智能服务",
                packageName = "com.miui.systemAdSolution",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAOiSURBVGiB3ZrNa1RXGMZ/7xioVYhdG4tWF4FUSxXrBzVtXBn8G7LpJlAC2Qgu7EI3\n" +
                        "+ie4Ebrof+DGldD4hWnKTDItLRFBXGQQN6UJpaGgfbq493bunDlz55w7k5lMHrgM9z1fzzPvec95\n" +
                        "77nX6CMkfQScAyacC6DhXCtm9me/xrZeGqfEv8pdX0R28TPwOLv6KSwIkg5JuiFpQ/3DRtrnoUEI\n" +
                        "2CdpUdJ6HwW4WE/H2LdTIk5Lqu6gABdVSaf7LWJa0uYARWTYlDTdLxGzQxDgYrZXETeHrSCHW2VF\n" +
                        "LA6buQeLsSLmhs24AHM+zm0boqQZ4AHwYSlX7jy2gatmtpQ3tghRsnavAGcGSKwMasA5M3ufGSpO\n" +
                        "hQV2vwhIOC7kDf97RElq8BMwOWBSZfECOG9mm9DqkQVGRwQkXFu8gqRxxSWA9zr1Lmne16AbK0lH\n" +
                        "JF2UdF3hudyGpPF8J1ciREjSQgGhs2WEOH18GcHlCjSn1sWYgYDVgrJql/KuMLNnwLeB1ZvcJT2J\n" +
                        "+AfWJR3I9yTpc+f+XohHJM0oyaoPdChfDeDzBKCiZI5dClQPsGZmfzu2U859LbCvm2ndFUlTnvIl\n" +
                        "j83FJUnjFeBC4KAZ1jw2l0Q1ss9P8e9f9wPbX6jQPBwIRYsQSZMpkTxqQD2y3yOuwU1DCjARK6RB\n" +
                        "eyBP4XjEzN4RPr0y7I+sn0e0kLqZvXVsU8AJT93Y6dULooX44uMzAE+wxnqkF0y4SWM3+IRkK54b\n" +
                        "rDXg12hKJVEhmfch2MQv5HD623LiYWb/MDivNGKE1M3sZd4g6TjJWr+Ef/kcmJAxwoW0ecPMXgGX\n" +
                        "C9oMKuDjPFJigBrwe2Dd165B0rHAto0KsBxQ8T3++CiEmW0TNr3+6FBvJnCo5TEz25L0lOJ8q077\n" +
                        "jr4fT3rj2Y2rgPfkA3hEkob8Zma/eMrPFnDK8NTMtsbSm4cUC1kzs38d2zHgR8f2jadtR4+YWcdD\n" +
                        "N0mfAF8XcMrwEJrPI92mly8+fPPXZ6uRPF8HQ9JB4HvgZED1ZWgKeU5x0Pviw0f6qGsws7+IWL2U\n" +
                        "nKv9QFh8NEi4t5yi3ABueyq/AN506MgdLDRb9eFjkgz4g4g235nZHdiLx0Gp4e6wWJXA3UwE7NUj\n" +
                        "07TgGslB8W7FNnAtLwLaz36zDW1+UKxKYD7iEXiPvOjJibk1bOY5lHv1lhMz+i9Dc2JG//V0Tszo\n" +
                        "fzCQEzP6n3A4gnbdRzV75jOnnoS40BA/PPsPeu6w84nPwM8AAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小爱同学",
                packageName = "com.miui.voiceassist",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADYAAAAyCAYAAAAX1CjLAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAIDSURBVGiB7ZoxaBRBFIb/t1xpo41VICAqItgkjam0N4W1hWKRPqB9mthE7BNIHwhi\n" +
                        "oWCR4rSIaDAEVAgELKyjVjFNis/idrnL3ezi7u3sOst8cLD7eDvv/5m7N3s7K3kGuA6sA7+Al8AD\n" +
                        "3zUbAegzycW2dU2NwxTAqu+6ie8COSz4LtCWMe9EY6ERjYVGr8pF6SJ7VdKOpD0zO6tTFLCSHr41\n" +
                        "s091jl1UdGlsTdoHbhfku+gX5D8Zy/3ox8lk4V2H0GcF+f9sDLick/+orM4qvzHX4po7YyW5kRN/\n" +
                        "WHagzjaPzhrrSRJwS9IlSd/M7Ge7ksoD3EkPd7MOnQCLkvrp5xjYBK61JbIswH0N9R8BL6TBV3FJ\n" +
                        "g9nKeCxpsXGFFQAuSFobCc1KWgZmEkn3HNe4Yv8j85KuOOI3O9s8orHQiMZCIxoLjWgsNKKx0IjG\n" +
                        "QiOR9MYRf9+0kIp8lvTdEf+RSNqQ9HskeCBpvQlV02JmJ5KejoW3zOywZ2avgbsa/tncM7PTZiVW\n" +
                        "x8xepfqz83dS+szDzL60JawOMjOjdLp5dJIqxj44Yl+nFZJymBOva/x8HJsSAHMF+dNuSlBFZ+lt\n" +
                        "JDPbAP5osI0kSTtmtl+leM74z9PHatn5SlF+a5SdsbqIzSM0orHQiMZq5th3gSaMTdygStpuoK5f\n" +
                        "OP8i5gHDdzi88hdIU5D64hVJLwAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米云服务",
                packageName = "com.miui.cloudservice",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAALESURBVFiF7ZdNaBNBFMd/b02bBiyCtKUgFHoSCoJSUXoS9OJNCoIHqZeCIHgQ7x5E\n" +
                        "wbPgRRCECiII9YN6EkFBBMGAWPwAi6IolaSRBhJtwk7+HpLWdTtJGpNeSv6wLDvz3rzf7Lx9Mwtd\n" +
                        "dbXFZK0YS5oATgC7a9dv4CfwGLhtZgsdJ6wDMibpgRorlHRD0vbNhpmU9KsJTFRvJO3aLJgJSaUW\n" +
                        "YKJQqU7D9Er6WCfgW0kzkuYkLdaxudJpoGlPkEVJkx7wy6rmUFQlSTs7BXNU0idPgD0NfC56JnCm\n" +
                        "XZC9kl7WWYI7TXxTWp/8d9uBmZC0XAdGkk5vYIwnUQfn3PzS0tKlXC73Ip/P/y4Wi1pZWck4565J\n" +
                        "Gm800J4mMJI0vQGguahDuVxWJpNRLpdTPp9XsVhUqVSSc27V5Kmk0egYidr9JrDDE+MekAYc1Yrc\n" +
                        "aR0CXkg6YmbvABKSjgLx1/cdOGlmzzYBIq5hYFbSfjMrBMCUx+hYh2G+AReAs8B9T/9u4DwAkj7E\n" +
                        "cuXJ/0b15VA2m32VyWT+2d/CMDzlydGMpG0BMBgbN/2/QD6Z2dTQ0FAh2pZIJGao5mdUg8B4AJRj\n" +
                        "HR3bg3p6ehYGBgbe1+l+7GkbCYCvscbDbTBkY8+9DWx9Ey8ErF+isY0UwTr6HHsekbRugpK2Ace9\n" +
                        "/pLG6xTCy2rxCKFqtY/ri6QDEZuUpOs+O6gdYSXNApOeGA543QAASYRhaM45nHPW19e3L5FI+MzT\n" +
                        "wA+qNW/Y03/OzK6uAg0Dr4CWTnqSqFQq1GBwziGJ/v7+VoYBmAf2m1k5ADCzH8Cx2gzaknOOQqHQ\n" +
                        "3PCvvlItxGWAYLXVzNLAQeBhu1ClUonl5WWcc81MHwH7zGztY/D+Bqn6u3Oc6uY3CnhPfr4lC8Nw\n" +
                        "7V6pVEilUiSTSZLJJEEQrOZkGrhlZs9bn25XXXW1xfQHr/RmrURpKCwAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "应用商店",
                packageName = "com.xiaomi.market",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAANzSURBVGiB7ZtPax1VGMZ/r5obK6FNoNKKGhBioq7EPy1Fja5NXIp+g4oIdZdkY9pP\n" +
                        "kI26tsFNPkBQ6KpoF81KQa2xASFijCQakoDa4s3jYs4M506ul3vPnT/JZH6bO5wz73Pe55xh5pxz\n" +
                        "Z6Cm2linSkkjwDvAq8CzwGPAw8BI/ql1xQ7wD/AbcAf4Glgys52eVCQNSroqaVfHj11FuQ+283Zo\n" +
                        "hCWdBZaBC7129xFjBZgys22/sMWw65WbwMUCE8uT28Ckmd2PCx5InfAR1TELkZd5vyAZYXcprwOn\n" +
                        "Ck4qb/4GRuNL2x/htwkz2zAzAz7MILn/44proxEQe4rIG9Bq+JXAZOJH1B+B8d3wZ6qtXkm8+Yaf\n" +
                        "DhSL474JjO+Gb1Nt9cpYfOAbfjJQ7JL7/R74NVCjExvAd6m2emU0PvANPxIoNgVgZgIWAzU6cd1p\n" +
                        "A7wZqJF48+/STQ4/prrhAJgwszVJ54CfgNOBiaXZB8bNbFPSGLAamqOZPUgqOEQojpsBMLPfgblA\n" +
                        "nXbMmtmmO56hvxxb6XP++q+kF52OSfq8Tz05DXOaL7k2gsnasCTdkTTktAYkXe9Da1HSgNMactp9\n" +
                        "kYdhSfpSUsPTvCxpq4f4LUnvefENSV9kkVhehiXppqJpaqw7LGlenUfpR0VLumEv7qzTyoRY179L\n" +
                        "H+6FcH4G3jWzlVSnjgPPA48C94kW7qtmdjd13svAEvBUVgm5qWlLI1nTlPSZM9kVksZdTDPrZBLj\n" +
                        "vuFMurKND6Ktl2Wi9ekqsOvqzgATRMu4KaKtpI7bTqHEI1yE4SNBbDj0QX5sqQ1Xndpw1akNV53a\n" +
                        "cBZYlwCPA5+kwue8+swpdYTNbMPMPiBaKBTCQ3mKS3oCeMYr8veV94EbZtYEPib6WzZ3cjUMvAV8\n" +
                        "2qH+DaI/737JOY+Esm9aQ+63sIVL3iPs8z6wDbwAzBbYbgtFGl42s3VJfxXY5iHKvqQLpzZcdWrD\n" +
                        "VafexKs6J9rwQWlZ5E/izTe8X0IiRZF48w2XOuXLmcSbb7iwJVoJrMcHvuG7bU6sCmvxgW/4VgmJ\n" +
                        "FEXi7eS+XOoKFsrKKkcW/JfET8IL4q+b2b24oGWm5SqmiV6fP+6sANO+WWgztXTDPwlcA/aKyS1T\n" +
                        "9ohyn0x/7wDdf8bzGvAccB4Y5Gh9xnMP2AR+AL4i5DOemgrxH3VlWjzgXbNOAAAAAElFTkSuQmCC").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = true,
                appName = "下载管理程序",
                packageName = "com.android.providers.downloads",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAHFSURBVGiB7ZmxSxxBGEd/X7DS1IKkEptArhGCglWwVa7WNoiVYG9r7R9gk8peEGyu\n" +
                        "v5BCOAQFC7W9XkJKX4pT2V2Pu7md2ZkNmVfOzsy+V+wuuytlMu0GOAAGuDMADlJ7lwC2Zwiosh3C\n" +
                        "4UOITSTtJFr7RqiQT4nWvhEqJDk5pG3kkLaRQ9pGDmkbOaRtzLlMAj5K+tqUBPBtwuErM/s9bQ9z\n" +
                        "PNGNpC+uYoG5NbPOtEmuIfj71MfMpnq6XiPHni4+hD03cOLxFliXk6ARhZjTiBGnjUQUYs4iRJw1\n" +
                        "GlGIOW8w4jxKxEvIAtBrIKIHLEQLeYlZBPoBI/rAYtSIQswycB0g4hpYThJRiOkA9x4R98DUJ3cU\n" +
                        "gHVgWCNiCKyn9i8BbAJPM0Q8AZupvccCdIFnh4hnoJvadyLArkPIbmpPJ4C9CRF7qf1mAjgcE3GY\n" +
                        "2qsWwFEh4ii1jxeMfgIF+ZmT+Zd49y4MzEvqSvocX8eJO0kXZvanOFgKAZYkXUpajShWh4GkLTMb\n" +
                        "vg5UPz7sq/0R0shxvzhQDVmL5+JNybUa8hhRxJeSazXkV0QRX0qu4+5aG5K+S1qJZTQjD5J+mNnP\n" +
                        "1CKZ/4K/9OOkaYhf6uEAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff72c476.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "天气",
                packageName = "com.miui.weather2",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAYAAABXuSs3AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAALnSURBVGiB7ZlNq1VVGICfd+tV0fIrbtC1Ij8QUrll5KwGEk7SuhHoIAeKiAMdOSlB\n" +
                        "/4CKMyc6DJsIoYIfaWiRiJEDCcpBpQM1FS8iWgZ6xafBPkeP9+7j3ft8bY/cZ3jWWe969lprr7X2\n" +
                        "u2CMMV5sopONqQuA1cAHwBtAH3AHuAb8DBwCTkbEUCe96qIuUk+bjz/VVWU7o25VH+SUrmW/Orks\n" +
                        "6R0NCNfyizolK3bSRule4O0mwywB9qkdfRcBUN9TDzbZ8+s7Ll7zACvV2w2K/+2w+V5oCNR5wApg\n" +
                        "HulS9hrwABgErgKngZ8iYrBO/TeBb0iXw6KsiYivi8hOVreov+XsnUfqMfVjM+amOrFSXpRv8won\n" +
                        "6gb1WgONVDmr9teR/65grMxRHB54qnq0CeFahtQvM9qYpJ4rGGvis6TnqBdaJF3LHnXcsLbmq/8W\n" +
                        "iNFXT3qm+lcbpKvszmhzfaXsoXpvlPovZUmPV0+2UbrKpoy2p1l5kdUe9S31c3WvT0bkVr3e3toB\n" +
                        "adX/1Nl15+pIr6nqJnVXVmGverdD4qqH84rXo3pW2Qa83GywAixX320mQKg9pDvftNY45WYv8BXw\n" +
                        "CfA+MAuYAlwHLgKHI+LXurXVZR2cIrXcd/Rz+u/qZ1neCTDQ6q7MyQSgZ5T/LAAOmJ4un1oKE2Bh\n" +
                        "u8xayABwRp1e/SEhPeV1A/3Afiu7bzeJAywDNkK6qvwDjNxKn18GgbkJcKNsk4L0Ap92ozjAQAKc\n" +
                        "L9uiAfoT4EjZFg3QF6ZfFbdIt9tu4V4SEfeBPWWbFOR69fD+KnCJ7un1AwlARNwEtpcsU4Qjj/Me\n" +
                        "agKcAD4qzycXt4HZj5OeEfEI+AK4XJpSPnZGxJ2sTNPrwHHSI+XzxhlgaUQMjUgzR8RV4EPgVMe1\n" +
                        "ns0fwMpc1yzqWnWwXZ9ABfhRfaXQY6oz1M3mT3q2ksvqOjOSp0XTzItJbwneARYDrb6jEbgCXAC+\n" +
                        "B36oLBpjjNEo/wOVy3FIguSj2QAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xff5f94e2.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "浏览器",
                packageName = "com.android.browser",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAR1SURBVGiBxZpviBVlFMafs20XohaqlVZXtM22NEkit7ZIipVkLSKswA+ZRBYFFhHF\n" +
                        "FtQXlySikkCCgiAqkOjDkqC1ESarGRa0ViDpRhRRebeyNSuJWjd+fZi57ds4M/u+c+fefeAyf945\n" +
                        "z3OeeWfunDn3mhoE4DRJ8+PPCUlVM5tolJ6VRQRcK2mtpBWSOiXNTTlsUlJV0rikYUnbzeyLMvTr\n" +
                        "MgLcIKlf0hpJiwrSfKzI1Dtm9mk9+QQDWAkMUS6mgK3A4mYYuBJ4rWQDSRwHngLmN8rEQw02kMRX\n" +
                        "wE1lm3ixySZc3FOWiZ2zaKKGx+o18eZsO3CwoaiJB2c78xSszMo39TkC3CXp1UJnoPHoNbNPkjtP\n" +
                        "MQLcLGlHU1IqhjFJq8zsiLuzJeXAB0oUnZB0UNJ+SV8rqrnqxRJJ9+ceAdxewnW8DbgDODdDYwnw\n" +
                        "OHCgDo3j5FUAwId1kA8RFY5eACrAAPB9Qb2tWcT31mFiwNdAiu5i4IMCmlPA8jTC9wqaWFAg+ZPA\n" +
                        "VYl9mwpob04SLypo4n/JeJroimP/Ah5OjL0eqP9ZkvyRAiY2FTDRBwwmeAad8YWE3zPLXYHQa3QM\n" +
                        "OMcz+RHnkwXXzEBgLptrgZXAQICNAbPgi9VOPiFfzful6IHY6ZtUjJOShgJjfHCnJJnZpKTdAXHz\n" +
                        "pMjIvEDBfWZ21OdAoprNF+uAK+L14YC4TklqVfiMvC9JwBxJv8dn8D8AI85mXyD35ZJGzWwPMCmp\n" +
                        "4hFTAdpbFT4j4/GyXdJRoM3M3BoqNHkXlzjrVUldnnGdrZLaAsWq8XJObRvoMrNj8faeGeLzjLr1\n" +
                        "07j8jZzVqukz7Isz4+U38bJN0kGgx8x+NLPMl58aADKGfkjR8cF4i6bPsC86JMnMxiV9G+/rlLQX\n" +
                        "WOjJkTVrboOuIyCnaovCZ8QVGHXWL5b0FtAdyOficIZOHibMbLLIjPQ768m+bY8iM0sDOWs4LEnA\n" +
                        "jQExVUlqiTvkPwUEXuOc9aclJd+fl0k6L4Cvhmed51OIkelZBJ4PKAkAtjixt6WM3+eTAbAWOAKM\n" +
                        "OfuWAhMBuaxxCfsCjQBc78S/khjzbl4Aq4AeZ3tbQA4ngDOShF8GGjmQiH8mMT6KU9V6mgqtfE9t\n" +
                        "WRG19EPxboJjY/KAABMLCuivSyO6DPi7ANlggqebqEs57GsEuLCA7kd5hKE3fQ0vkfLujsczBbi1\n" +
                        "oOb6PNJuwr4xXBwjujx7PZKvAHeT/9aYh10zaQh4siC5ixFgPbAMaI8T7wKuBp4ADtXJf4uPkdOB\n" +
                        "vXUKNRIvpOWd1Y2fK+lzhRVuzcAuM+tPG8j8eZqoXirlN/CS8J2ZnZ81mNaNlySZ2SFJ1zUkpXD8\n" +
                        "lmdCyjEiSWa2T9Fb2i9lZhWInWZ2dilMRN88b8/Cjb1l5uzCzVSAl5tk4GcC67QihlYAbzTIwJ/A\n" +
                        "c8AFDTWRMLQa2FGSgV+JypxLm2YgxdBFwKOEN8KngO3ABjwb4nko7f9akgR0SOrV9B/Oap8/FDU5\n" +
                        "qs5yt5n9U5b2v8i29BvbxMLkAAAAAElFTkSuQmCC").bitmap,
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
                iconColor = 0xff6bb8f1.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = true,
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
                iconColor = 0xff7fce7b.toInt(),
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
                iconColor = 0xff5da261.toInt(),
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
                iconColor = 0xff5fa7ee.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "淘宝",
                packageName = "com.taobao.taobao",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATJSURBVGiB7Zl7iFZFGMafqTXNCNm80AaFbDfBLtTWmmG1pgSJLRJEV0GlP6otLMou\n" +
                        "FGVUVBRZaYGxsBZe0Mo1wZagbc0sL9ViooYrWWIlZKXbplBkv/4487FnzzeX8+3nLgTfAwe+mXne\n" +
                        "d95n5sx878yRKqigggqAMcDlwLnA0ADvAWAX8CxQN5gxRgGspS/2AQ0e7h6KsQaYA4wajHiNJ7Dv\n" +
                        "JY312Iw3xuxKcSdK+sLDXWKMme3rHBghaaSkUfYp/B4iqUvSbkldxphjER1O5y85RjeNrRn+6wHu\n" +
                        "1Ax3AvCQne3DkX4KOAJ0Ai8DV5Yi5MMczi9K8V2vFcD7wDXAE8BHwNGcgcfQDtyaR8jiiKM/gGGW\n" +
                        "e3+Ad6yE4A4BXcBGoMM+OyI2D8eEzIk4WGh5NcAPJQSbxmFgOXAHcFoglknA2wE/HTEx8z2GrSnO\n" +
                        "iyUG3wk8B0yKvhbF8cwM+J0fM74MWAi0ASuAp1JtlwJ/5RRwAGgGbgROLlVEqs/PPP7DsxJwOAL4\n" +
                        "KhD4EWCpp+2+MoQ8FuizeICAYcBVAYftHmfrgNnAdZ72D/orwvY7KyBkvMugleR1mOVoW+Jx9Clw\n" +
                        "kuVscbT/ApxdppCGgJDGqgz5LkkzbLEFqDXGPGnb6uXOBPZIut0Y8zfJwqt3cHZKagb6JcIYMzlC\n" +
                        "6R0kYJxH7VJSCSNwE/CxbevBJonA1MCIlYMO6z80I4vSQjYFiG3A6BS3GngEmGbLI4Evj1fkGeQR\n" +
                        "0mZSwdVJmitppmf6Nit5hfZmG4A3Jd2dqX5G0if2d2iLjL02MsasJ8m8fX42F9WQ/Pl841G+jUxa\n" +
                        "jjsTeCXDCcF5NHDEFZqRbT6jauAFj9FbDv6qSPtAC+mKGU8HfnIYjnZwVwHLPH4GWsiPzoNVxkG9\n" +
                        "pC2Z6jpjTKdtz5MihIJdHzM2xkwmvEYOVXka0k62Au2SpgRouUa1n7ZRoZKGn5Czs42Z8v6cdoOF\n" +
                        "E6MzYpFeEz3GmIOp8tP2CaGs7TcHuqMM4Exge2phlZw2l7LYSTKM50nyvUtS9aHF/l2eV2uRpAtT\n" +
                        "5R0p574zwoOlii0IlvStpEclnS4pfJztRXdQCPCqpMZM9YbU74s9pj/nDCCL1kz5FmB4Dju/EJIT\n" +
                        "4dxM9U5jzLup8r8e8wM5OndhjaMuz6y4hVgRrrPwe5nybzk6yQWS849r04hf/UjdRbsWcJvcIiRp\n" +
                        "Vab8q6RaB28e8LUxpidHEGMJ32yeB0yI+Og7IyTXn840Q1Jz+qrUYruHO01SO9BE5rbRgRb1FXHQ\n" +
                        "wbkn4qN3+wUmRrbJK7LWhM/R/cEbwM2etnkBu+mFgKZEOnjHNQzAUJJbwXLwO8l1UeGQNsTBaQFW\n" +
                        "B3zUCLghR2dX++YUqKX4E0QM+0my5SagxuGzneQ4fSdwCnA+iWAXlklSlaRrI+/fSmPMBl+jPTE2\n" +
                        "kkxvg5I/sjGS/pH0Z+rpkbRX0ibHWsviNWPMWivqHEkLJFV7uMvTI3AB/jN3KOstG8C9JHfB64DH\n" +
                        "SVKRwlNIVXxY4HPalCGuHkgRts8VgUBD+Bz7VcDn+CxgpSVfPwhC9vVDxGJgXN4OfLcpxxU2qNBd\n" +
                        "cgFdluv82Bo96g4mSHawMySdmqo+Kmm3MSZ+5qigggoq+N/gP1QTaE5ZCdJjAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffe2874d.toInt(),
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
                iconColor = 0xffe89b26.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "抖音",
                packageName = "com.ss.android.ugc.aweme",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAJ5SURBVGiB7Zm9axRBGId/r0g6xQiHCBaChZgiRRQOg8UFwUptJI2CjSDYWKWw0z/A\n" +
                        "2j8gEey1sPQQwYCG4OFXpfhRGMU0JopYPBZ7J0uytzOzc/sRuQeuuZt55/ewOzuzc9KYMaVgVQ0E\n" +
                        "PMoNYjZXVZYoyOcrsDem/q5RBY2kJelYTIGmiEj/kcjpugN44ZgjA24UrV/lUwvPpnvMbCO0fpNu\n" +
                        "rQGP6w6Qi+etleZ83ZkzKSACsATMAvtc9Zs4R7LYkNST9EbSXTPrbm2wU0TSzGWJNHGyF2IsMmLe\n" +
                        "xxZoisgHSRclLRctECUCtIHbwDPgI/Ab+Aw8Ca1lZvfM7KSkc5IexOTyBrgMLOctABl98tj20gWc\n" +
                        "ARaAReBFqm1nFAKzwEOflSxWZMj4k9EiQAf44SNRlkgeuz0lpiRFD1Ymzsnel3hVQZYockWACUlL\n" +
                        "FWWJwnVFrkuaCai3IulX8TglALSATx7zeh24QOo4B2hl1Kttss9LOuTovyrplJn9TH9pZt9ig4WS\n" +
                        "d2vNO/p2zWxmq0RdZIr0J3newrMp6UopiQoy7IoccPR7bWbvRh0mhqIivVEHiaXo7vdPSGPgoKPJ\n" +
                        "ZsEc/xgmsuboNx04znHH718C622jsAhwNGCcE47fXeMVB7jvWMS8TgSBKY9FtV2myCWPALc86rx0\n" +
                        "1HhemkQ/wH78tijLwFlSp4HAEeCqR1+Am6WK9AMteIYZsEryzu7LW2CyCpEJYCVQJoRrpUukZDol\n" +
                        "SSxWJlGiTH2vzSSP0e8jkCh/cnvKeB0HZbDeCIk0JAd0Tz0F1oA7hO0Egon6fwQ4rOS9pa1k/zWt\n" +
                        "ZLvR63+6Wf9ljBmzg/gLaUCZZSoIdmYAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "快手",
                packageName = "com.smile.gifmaker",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATHSURBVGiBzZptiBZVFMf/R7ZNhQjckoxeNqwPbrZs2habRFZkVmZJKGYSRPQGRVpf\n" +
                        "IoLdCCPQ+mBEoEUFZbUF+aXEvuzaC2agbaYLS7ZL0btltfmWSb8+zDx6m2dm7p15Ztv+cJl57jn3\n" +
                        "nPOfmXvvmTOPqUEAJ0laIKlT0hmSJkkakPSZpAEz29OojzEFcBqwFhglH68CF4x3vKkAFgNfeAi4\n" +
                        "+A14eLzj/hdiEmXx0njHL0kC7muARA2PjzeJWcDhCogA3FF1fFaAyMuSbgtQ/U7Sdkk7JM2SNFvS\n" +
                        "6QmdEUltZnY44aNVUoekVkl/SxqutaRuKQDzA6/0+ozx61N0lznyTuDjHLt7gEVVEHkugMRCj43u\n" +
                        "hP47cf884FCAfYBVjRL51OOgJ8BGM/VX/aFAAi6eLEui2WP4ADAt0NaNBQL+BuiLjy7+AjrLEGn1\n" +
                        "ONxRwNa0QBIrEuNWJORvlCHS5XG6oaC9rzz2ujPGJefYdFc+IcD3AY/8h1ASMX7OkY1KWpchWyfJ\n" +
                        "XYLbXWEIkR898gsDbEiSgBMl5SWQW83s+zRB3P+10zXblXuJmJmPyPk+GwnnJ+TIv/SM/905n+MK\n" +
                        "Qu6IJPXnyE4FHgi0c7dHfrlHfpZzviXQ53EAqz0TdBiY6rERmh0szRi/NKF3XRkiM4H9ngDeJ+MF\n" +
                        "Cpgbkw3BEDA3ZfxQQi9o70oLZlVAEHuBlUBXPKYr/u27CEkcAl4BeuJjMoVZm4yvSPY7WdJOSdN9\n" +
                        "umOMfZIuSdYCQie7zOygpMVVR1UCz1RS0ADagJGCj0oV2AcsqeBC1JHp/Y8I/EI0T3ITxeA5kkHo\n" +
                        "dkm3SroqcMgRRSlPaNtmZptCDDdEpAaipbBWoGtW9KpbF5iZHanCXxoqIVIUQJOkKZJanJb1e6/T\n" +
                        "NprZQJrNMSECnCtpeSI49/zkBszfaWbPJzsziQC9kprkVDJ0vKJxNGfcNEWVlLFCv5ldkexsygjm\n" +
                        "CWXvGUeBEUWkRuI2LOmjONU+JGm3imXF1YPyhbjPgfNiG23ArsZX3lT0hRKZmxyIv+Jew3bg7IrJ\n" +
                        "DAEbgEeAhcA5ZYj0Of0zgGXAGg+5rcSZaQkyo7HtNcAtwMyiT1QWkXdz9CYClwI/pQS0BWgpSGYX\n" +
                        "0JbjbypwJQE1tDQi3rIL9dWNGt4j+poVSubZWHcy0AEsAR4FXge+dfQ+TIsjddVy8EcA96wl/GpJ\n" +
                        "rwE3m9kgUcLXq+zVbG98vFhS3oSelNbpS+NDiOThekVkzMwGJb3VoD1JmpjW6SOyvwLHi+QvKhRB\n" +
                        "qTsyucIAqkKpOzJlDAIJwRJJZ0p6LEWWSsQ32VsajagozKxfOpazpSH10fIRmdFIUDFWmlk/Ud3r\n" +
                        "phy97ngfGZQ0T1JXhl7qHakD9SnKtR79npy94WlH70XPPhKK3rJENgPtOfpZRN52dO6vgMBOiv7p\n" +
                        "gPqq3ijRd8TlQDswydFNI/Ir0BHL5wAHSwS+G3gBuBe4qBABJ7iQfzfUPo2l5VorHFvdGTpJfAI8\n" +
                        "RZSahGW4DvLeEBdIukvSDQVtbjSzuk/JwGWSrpF0StxaJH0Qt21mNlrQTzEQVdFXE71r+HDskfpf\n" +
                        "gyiLfRDYBPyZQuSe8YqtdBUFmCBpvqJsFUmDZvZmVYEVxT/gE25lNuhldAAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xfff03200.toInt(),
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
                iconColor = 0xffef6696.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "知乎",
                packageName = "com.zhihu.android",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAN3SURBVGiB7ZpLiBRXFIa/X5QYjAnIDIFBiIK6SMhCAgoK0iNiRFyoqCiCRCUGXLhx\n" +
                        "52rwsU0cRBRxIROCoCARRBAce5JFHgYSyEMRDC5iFjJRXMS4/F10Vbosu6vqVlVPj+IHDbfqnHvu\n" +
                        "+bvurXuqukUOthcC7+X59ZhHkn7NclCnk7b3AKuBlcCCHiRWhgdAE/hG0ulcb9tfe/pzx/bHybyV\n" +
                        "EuFav8Pe86Gk3wFmxGdsj/Qvn9KceO7I9s6+TpRq7Ib2FVkz1V9ljWyAtpAP+phIVVZBW8j7JYP8\n" +
                        "A1ysJZ3yDNheOjM6mFMiwDZJF20/rTOrkrwzI9+nI7GIjcDsOjMqSxkh5yXF02lTnclUYWa+ywuM\n" +
                        "AtieQ6uMCWWiRB+ARpZRELSjj0taE/VZDywr2G8L7TvjsKRgMTk5DodekRtxQ9JV4GrBJKDHt/hQ\n" +
                        "IeMlx7nZ6aTzy6KJoKtXsBT4LeG/L2lInF9bIE4jYNyivo2Qu1byauzr4vNmQLxaKSpkEjgOYPsC\n" +
                        "8FEXv77tKUWFjABDtseArRl+b1RPqRxFF/vJgn59E1K2ROnGayFVeWWEhGyIu4C/cny+A4a72JoB\n" +
                        "YwVTVMiYpC/znLJ2Ydt/0MMypaiQa8ldNk3BMuIHpoGQrzJsl4hKc9tNcsrtXlHHYr9bQ4zK1CHk\n" +
                        "dqLdt/fEdQi5CWB7gNYU21VDzGDKPOom+UnSrai9UtJuANsLgMMVYwdR9Yok32ntsD0XQNIR4EzF\n" +
                        "2EFUEfIncA7A9ipgM3AhNkr6DLhcKbsAqgj5XNJk1N4GzALW2R5N+OyltX90o9P+M5H4hFHgkTNN\n" +
                        "M9F3yPbfKfuBhH1Ft8fXyH4vOv+LWy/8yuTYKLvYv0i0twNDKfuo7ceSxoDM3/5oPevMk3SoZC5A\n" +
                        "+HstgP2STkX93gK+BZZ28HsC7JR0ORX//ykjadj2u5IeRPE2AAe7jJtVMbQK1YApdT3Z2/axHP+f\n" +
                        "bTcy7IOJWEcD8kjTCBFyMiXik4L9mhm2/bbn2x4vGKsbhYV8Gvktsr3D9vmKA8c0bf9XQ5xGvEYm\n" +
                        "gYGMOTjdWRzvI3f6mkY17ku6+yoI+R7aO/uNDMfpzpXnjmyP1LDoppp2bZcS87L9hWNA0kNIFY2S\n" +
                        "xBRWrBWYAN6ORUD235yWA0tovfkY7OQ3hfxLq2a7Bfwo6Wyf8+kdzwC8NyXjf0uu9wAAAABJRU5E\n" +
                        "rkJggg==").bitmap,
                iconColor = 0xff4a82fc.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小红书",
                packageName = "com.xingin.xhs",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAJ+SURBVGiB7ZkrjxVBEIXPIYTwC9BAEBgSDGYFWcCRiyGAwWzYDQ4C4WF4LQqBQSIJ\n" +
                        "CeZKwCGAgCAIXmIBxeJY+BEHsUNyM7e7pqt7tgcxXzKip27VrcrpnurpAUZGRiyY4yRp1QxKmvb/\n" +
                        "AkmLsjk4RF7bM3zuGLYpyU/tm5IWM/5nDpKv+oiTosauiN9qh18SVm7bnLXcMmwPSP52xuuN5EKa\n" +
                        "6XE0Zid5uZeMMvEocs2wXS9NpJSkQho1jkfMv0je7y+lPFIVuWDYrvaRSCmdhTRqnIyYP5B80m9K\n" +
                        "eaT0kRXDdgVI7hOhHmD59dMzAEDSQsejfSppybD/MGJ39RZXE+1S5FyH/XRzxbjrSaaE6BqRtAfA\n" +
                        "ckHsdZKPCvxdWIv9WGHsamoAdiFnC+JWVQOwC/lcELeqGoC92O9hs9ADEXvsqVJdDcAohOQGgIsh\n" +
                        "m6SvRszqagD+bTwknQKwP2IeRA0goxDYO91B1ACchUiaADgUMQ+mBuBX5JJhG0wNwPeGOIHRJIdU\n" +
                        "A/Cdopy3jJJetu+RPOLOKJOkQho1TnT8rN1Xun7fK6lTy9rhhnhL8rk3mRJS3hB3ADjjjBtspFtJ\n" +
                        "iiJ7Aex0xHxM8mNmPtl0FkLyG4B3jpiDHEakPrWWsbmYg0eis5D8U5RRJkmFkFwDsLbFuRRhTi1J\n" +
                        "E0m7ayVTgvmhR9JT+PrBYZJvZvznmmSL3OOg18kfkxo1PLxo+VvHRKXMFWFNLXNLEuBGa2x9EOqd\n" +
                        "YCFK25LM8ozk+xn/JQBV11ZMEa8aN1vjqmoAgUIy1JiS/DLjX10NIKyIV43brXF1NYBwQ3wIYAPA\n" +
                        "vgT/nyS//xs0aqw318jIyEj//AXLwAqzwAEk3wAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffdb2c42.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米商城",
                packageName = "com.xiaomi.shop",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAHlSURBVGiB7ZqtbhtBFIW/syqrZFKFlAW3oKQ/KDXKGwQF5AlaXhSzqq9QGCmopLwk\n" +
                        "CqoTEhIWJSwkUkHU4FPgdeVsbGt2Z+2ZXfmTVpbGnjvn2Ds71zNXBGJ7COwBb4Gt8noe2j+QB+Cu\n" +
                        "vM6BH5JOoqPaHtj+ZPvM6TgrNQyamhjZvk5ooMq17VETE7kSZsZ5m5iy3Iztw9QKa3DYBxNT/v8y\n" +
                        "Kk0MgAtgO3Aa5cIN8EbSfVE2HNA9EzDRfAAwNbKfTks0+wCy/R74nVhMLB8KJmlH19krgJ3UKlpg\n" +
                        "p2CS/HWdLdn+S/tZ7Lp5kG3X6BCTUr8AXpavobGHwdHrLKMNxM8bb1EuN6p8blhHW7FowFUhaQTs\n" +
                        "th137UYAJP0CvrYZ81lM5+rtsIBbSd/ntP8EvsSMP0uUEeAjARPS9mdJryvNV5FjP2Jdt9Yr299m\n" +
                        "GyT9aXOAdc6Rd6sM3qaRE1Ugbt2pRZKn1irYGMmNjZHc2BjJjY2R3OiNkdi/utXMN+b9SyYnVcv6\n" +
                        "L6Q3mw8FT7+FLnLXKyOnqVW0wGl/NrEljYFxaiURjCWNp+vIcVIpcRxD347eJN0DR2k1NeKo1P6Y\n" +
                        "JfuyOTL/eHrGTBeOqYOrH3I206gepdtFNTNmOlPmpBqmsi48+wccWLnZenD1xAAAAABJRU5ErkJg\n" +
                        "gg==").bitmap,
                iconColor = 0xfff16033.toInt(),
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "饿了么",
                packageName = "me.ele",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAQVSURBVGiBxZptiBVVHMafs7UtvRushbZGi66YkC/Yh7UIckPIMlqCtSgRDSrDXv3W\n" +
                        "h9i1vpSQ9EIkfWijFowtMMn8ECVqtCpspSVUrCJUK8mmLbYS9MKvD+deGG4zc86ZObP3geFymXOe\n" +
                        "5/+bM3OYc+41iiygR1KPpJmS2lM+90naL2lE0qgx5myMXFPWAJgpaYWkOyTdK+nKQIsDkkYlDRlj\n" +
                        "vilbT7CA9cAu4C/i6G/gNWDBdAHMB96LVHya/gC2AtdXCbEROFUhRFITwBagNSbAIuCDaQJo1CfA\n" +
                        "NTEgVgNnmwRR12HghjIQy4HJJkPUdQK4pQjEAmAsMGwE2Ab0AR0ZvkuAp4GdwO+B/pPA3SEQVwMH\n" +
                        "AwI+osDVAi4GeoFvA7ImgPk+5hcCuz1NfwAeCgXIyH0zAOYtH8PnPc2GgfYYEInsWcBRz/w784w6\n" +
                        "sUPnhIgJkFKHj/bmGWxtNkStjmFPmCfTOi8Ephwdd1UMcD925vPVT8AljSave3S8tSKApcCOAICk\n" +
                        "liaNlgH/OjpsqwDgUqAfOF8QYrDRcKOjw0lgdmSItcCRggC/AJvTTF1z+KaIAN3AhwUBAF4Brssy\n" +
                        "/zKn4z9EGA2gHXgR9y2cpZ24nlHsYiZL+yNAPAz8WBDgK+BBn5AbHUYvlABYgV1PFNEE8CzQ5hu2\n" +
                        "1mHYXQBgDvBqQQCwz2xXaOhzDtMLAv0WAscKAuwBbg8CSARvdphfFej3aQGAY5R9iwYedYSkT3Xp\n" +
                        "XrcFApwDBoArCtS9CphV/94iacrRJzjEU4OSbjbGDBhjzvl2wi6/d0jaI+nt5Ilex1VbHlId7mn2\n" +
                        "c+CuEM+abxfp74Pr6g1WOoKfCgzsy/AZAx4LBah59gNnMnwPSpLB3mencny+NsYsCwxeLWmd7Mb1\n" +
                        "pKS9kt43xkwUBMHRpLPecNQxKuHbMBEFfOGob31Lre1uh9d9VRfr0EuO85YD6HEQnyZgGq5CwPaM\n" +
                        "2n4FFtUbtQLjDpjtTQbpxK5DkjoO9DY29NlXuqdJHPUaH0/U8gZpywvsW/CfDpBDwEVNYEjWOYCd\n" +
                        "FXMbvewxKv3TVHNxAXOB3zxghoDLm11vrvDfMj0E3NTsejMFtAEHPGHOYHdgLouQey32/n+A5F5V\n" +
                        "SdMu/PaA6zqOfR+aWyBrDfBug995Ap/FzN/ZsSu1zwLrmpI0JOl7Sacljcu+x41LapU0I3H0Sloj\n" +
                        "aU6O3wZjzDuBNfxfwIaAUalC4yQWT3lqyTtpjBmUNFD6ihTXbEmP+DTMBZEkY8wWSd1yrySr0oyo\n" +
                        "bkAH8PE031p9USEagJ4Bfq4YYJjAJXZRmA7sz9CxNUKVo5ADNA94AruxVlRj2H8FrSpTS+n/ayWg\n" +
                        "2iStlLRE0uLa57yGZlOSjtSOo5IOG2O+i5H/HwFzQUa5uwjjAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff5a9fec.toInt(),
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米有品",
                packageName = "com.xiaomi.youpin",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMgSURBVGiB7VnbVRsxEL2Tw3+cCthUwHaQpQJIBTgdkAqwKwjpACoIqcB2BTEV2HSw\n" +
                        "Hdx87MiWhKTVmrX9o3uOj1lZq9G87owEUFBQUFBQcGSQrEk+kKzPvZcPgeSGe2xIVufe02CQrOji\n" +
                        "6Qzy78ZY6NZSoiU5GWF/ubJvSP5T2YuU7IuM9ey82ACoAKw9gTWAzwAaAPcAtgB+isgysclHAFeB\n" +
                        "nyaeTIMGwBTAY2zBysuBMTFNKLI8YL1obl4AaAH8VktArTHRj22xNwBD86PNmLMSkSb2I8kGwAIA\n" +
                        "RGQ7UP5ukZcc6x64tvFINPx0XqPz1ql5n3rkfdPvNxE5KVsFkPRuVBH1gAm3WZ8UnpDNQkh5xHB3\n" +
                        "0hvUqg9gw44qxy6W0fyxEaRfTTCzwCVJZgqt0Xlv1HzKQayOPHjPK+tvn818nKV9eaeIhobjzhQ9\n" +
                        "6js7isSeIHJxRXIRGDdlIAshj/Qm9siYIC8PkjTtJLta9g7AM9xwOib+ArgG8FU8APgCYK7zksVQ\n" +
                        "7Ad18ZuITLVQmTDxLeY/V7ASXDeRBLteqxWRZARoqL+ISN5ZSCvo1Ho+pBciyZy2ZBByatSFPTFR\n" +
                        "L+aRcRuG6ZKthA+V/cvICfVTItJ671QA/qDLrycRmUdDwA6tzFAxtWbXBGrOxVjMtOt2mC5F5Jrx\n" +
                        "Fh/owtin+O8555Fe6IZDaODWpBCBvGK/abNODavPg9t1r9ApYp8a6yxFSIasavN8ViUP1SOvBpmw\n" +
                        "sbH1CcFi1x1yPZLk8A/CZ6ODOoNcRWLJbizc17YA6BLbT1wMqN4pZCmSwfVOeCRQ40je7TtYHYJB\n" +
                        "9DsSlsdQZGhB9HPkIEOMQr8DcBdgQCe3RKT1jj+XenCz4RNCO5YiuVbso+nXwFgF4AfcptHxoois\n" +
                        "U4qs0RWl0OIOPCvayeyHmb8hoLvQu7Fk+u89i4hjAJK36FoU9O6P5EybwPDN3vv5BrU11ljjQbbi\n" +
                        "/kq2NcWQ5L2OrUMNI9376PtcRbIO/yS3fudLcsKui57FOlidM6P3L4s+uapMf/FUa2afFtld5p38\n" +
                        "0qGgoKCgoKDAwn/l6rwP+kBscwAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffee552a.toInt(),
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "携程",
                packageName = "ctrip.android.view",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASNSURBVGiB1ZpJbFVVHMZ/fxGDWsR0oRJRcUhFrEpSWk0c8gxuYGF08YgMJsa4cUFM\n" +
                        "TFyxkCbUhXGJJq0LNOIQJGoIqGGwgMUYaIoDoCJRUArRYlEx1ejic3Hvay6HO5w7vFf9kpt3hv/0\n" +
                        "3XPuGZ/RQkjqBLqBHmCeU/0b8Amw18z2tjIuL0i6QdI6Scflj+2SuqY6dgAkTZP0jKRTOQi4WDXV\n" +
                        "JJZK+rQEgSjunSoSL1REoIFNrSYwU9IbBYMdDJ8kXJHl/8KKSHQA/UCtgHov8DGwCGgDFsbIXAv8\n" +
                        "nGbECjg+B5JqBCQ6CqhPADcBjwMLzKwuSTFym4GfgDFgyMw+yBtkXdJGSfslfStpTNI/kr6XtFXS\n" +
                        "85J+L9idJGkk9LM+p95GXwI9knaUCDAPuiUtVvBy8uDRaMwXxLUCsIOgz7YCHxJ0r17gWA69A4k1\n" +
                        "CrrS/wF9buxuiyzP8UaahV7gHeCvhPp1ZrbaLZwctSTNBg4DM4BXCEYJgKeBmZWGmo0+YBNwF3Bl\n" +
                        "WDYG/GBmW+IUokRqAGa2yxWSYofEZuMssN/MvL5Vr3lE0q/ArDJReeIosBPYA+wxsxOVWZa0rAUf\n" +
                        "7+uSFlcWdAKRrU0msaaKOM+bRxwSHcCSKhylYKQKI6lEgHoVTjKQPLHlQBaRZrfGl2b2YxWGEolI\n" +
                        "mkVwSFAGu4BDKfWbS9rPhqRayY+4HtqZL+lgTP1E+A1WgrSNVdwGxxf3NyZWMzssaVqMzKtmdqSE\n" +
                        "Dz9Ieq9IMyTYcvcsfvuJioh8UYDH7TF2XnJk1reMRBjA6ZwknoixsTZS/76kx5oVb+JaS9LfwHRP\n" +
                        "OzvN7IFqQpr0XwduDbOHzOztooZO5GiNBytjEPheE+OjWGtKOuBJYjBG99kSJOb6+okibWb33T+/\n" +
                        "5gQyAzjtqRuHuQnlNUnzk5TSiOz2dPyRk18JjHrq5sE48KKCE0n/FbOk+zy61dEYvd2SuotGq/gV\n" +
                        "xXMxZX7LG0ltkn7JIDLo6EwPy6+ukEhaDLMbeoldy8z+APIdTUJ7qFuma13m5NOW+Tc3ElnL+Cwi\n" +
                        "Vzn5dghuqTL00nCnk087fBhuJHyIjKfUz1MwSjXQHv6WIZI4MjkYCXsNkEHEzMaBDRkGF0TSjS51\n" +
                        "nWcw50DSLfhv5vZFM1ktArAW+CqlfvINmtkx4AwwxzMYFyuBizxlP89tXdLylJFjwJEdkjQaHVE8\n" +
                        "ffRkjJAuiu2XlH6HcU9EbnVYlmuZkpNE8f2MpIsVXPjE4a2IXGdYNqqUJUVE/qmcJKSyi1RJc5R8\n" +
                        "Q7UkIvduWHYwjoykdkkrJG0oQGIoLrbcd4iS7gA+i6k6Aqwws2EFt7DfAJcTnKI0rpjbgK7wKXrC\n" +
                        "v7Tw3sSFpK6Et3VS0qJQZmGBt52F5hwYSuqPcXZW0sNh/TWShv/TJCJkViU47lfQcpdIerkEgS2K\n" +
                        "jIrNJtMpqU/SdymE7lZwxDThSeC4pCd9Yyj9hwGH0KXAMuARzl/svQlsA25siAMPAbc5cieBAWDA\n" +
                        "zE75+q6USBSSricIsjN8GumJyPNnJP01sB3YZmZn8vr7F+fWfFWqd/hEAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff3d68d2.toInt(),
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "App分享",
                packageName = "info.muge.appshare",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwBAMAAAClLOS0AAAAIVBMVEVHcEz/////////////////\n" +
                        "//////////////////////+PBM77AAAACnRSTlMASSy/Em7v2KaRY7oCgwAAAXJJREFUOMvNUztL\n" +
                        "w1AUTmqK6RZELHaqQkU6FV9gpopiHfvCIZN2CJhJqIJkChQUM3VwyqaheXy/0qQ35yY3zeTkmc49\n" +
                        "3z2v735Xkv5uysFOZbzzYYbtivgEiQ034800XpFxBwSXo92NeM1GOK5qvIdgSr48LSRYOOGHlt/n\n" +
                        "/gBRfsvAC/ddOOTeHDYRUooMn2pOTFjg17Yxy7x7rO2BV802bqRRE1hmgE5FDfijpAeNVcOKnEAr\n" +
                        "TlWnFg08Cnuo6NE6jrD5gJp1oQlEHdFQRigy2KKLHt9zrgmAG4ldOaDHGbDFgG4F4AjN9WxR6ZbN\n" +
                        "eU1sHn9JNPeQLfxcemkDbfYc3yVAZ6wodlTSDLHqQRSaih/q1StRsSS6V4Lq7SCroFgCrSriPHdW\n" +
                        "ANy8ciICraDvMJeih5iLzF4/cJ7yyhDZFX+JAbwlv0Pp6MBZcUQ5CQTzxUUiuCeRhrrO1Im4/N2u\n" +
                        "3tNwcL75DZX9xennuC/9C/sFYJSEEykCAlEAAAAASUVORK5CYII=").bitmap,
                contributorName = "lamprose"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米社区",
                packageName = "com.xiaomi.vipaccount",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAWvSURBVFiF7ZhtjF5FFcd/Z3a322238qrUgm2gtkiQkpbEIBqMkmqAluALxsSX9Isv\n" +
                        "GI0GJRpMTKrVD8SY2kSNUlMTbAjSoAaKQtQoQUFUTI2Ltg0vpVBYahbKamm3vffnh5mne3v3eXa7\n" +
                        "fJSe5Mkz95z/nPnPuTNzzlw4Kf/nEm2F+jpgBdAHPBoRe7tgVgFvB/qB7RGxs9cA6unAIuAw4Cy4\n" +
                        "CQz2dzFcBqwvBD8JTCEIrAWuLwRHgJ4EgQ8AHwNefAUEF3QjuJocQYCDPTpfBJxV2i/OMNAa4G2z\n" +
                        "IHac9AOo/cBm4FTgrcX2MnCzOg6sj4gdjX6nlf9ngKd7OVcTMNRQ7S74eSfIb7Dj6E1OLxc2Bu1X\n" +
                        "R4r+AXVwGoJnNLAH1EvbE1DnNX5zW/Zjr3gI+ENpv0TePKvJ6/CRiBhp9DubEkF1LKV0eJoInA4s\n" +
                        "Lu1/A39TB4D5QIqIMVrLSB0u/SIi9vSa+dWN6H2pZVupHlatqmrDNORQL234+XPRrVVfUJ+rqup7\n" +
                        "Lfy71ScK/h/qvCmbRD0D+H55fBy4Vb2OvB5eAN4BzCn2ReqVwCklKg+0jpyzG+3nyv/9wBPAypTS\n" +
                        "9eovI+Iu9SLgp8XXGLAuIqZuUvWOxqwvV5fPsD6bck3L12cbth+0IlsX/cPqG9SHyvNR9V29XsmN\n" +
                        "HW91XW8vuk/NguCKlr+NDdtNLdudRT+hPt3AfaYXuTaR3eqb1dPUj6jvV69tzPSA+jn1mmJ/nzqv\n" +
                        "5fNnDX8fatkuVp9t2Cv1h73IfaVHRB4zp6om9hfF9s+uziZxfeofC/aoenkXTHM57ejmB3Vzi1Tl\n" +
                        "5PpQHbWcg+qZ6s6iv38GgsPqnoI9rJ7fsq9Rn2+MM2a3tdcAvKweKuS+UVXVpk6k1GUFu7zgVH88\n" +
                        "A8ELij/VJ9UFDdslhbStYOw0n5PHJJGPjt8DHwWOkA/pkZTSDeSi4YqI2F3wi4HOaT86HUFgKZ1U\n" +
                        "BY9FxHgh91pgK/moOgq8B7iz4JYDN7ZnerE61+MP56t6RGVdA/P56dipHz+2Zqpqa9HNV//S8PHF\n" +
                        "on9LI6Lj6nkdPykidkTEIeCDRfdf4NEe476+0d43HUFg4bFBUhpRB9V7gEuKemtEfAsgIh4GthT9\n" +
                        "MHCvZXM2M0ln8J10rwGp63pxSgmgBrrnyUk5p9F+FhiMiH8BfyXn5Y0t/JfJwVlIrnbmkzMKqKda\n" +
                        "Dsuqqm7vNWJd1/eW1/CM01QxxedvGq9yyhFzotKJ4AVM5s1dZYBTyFE4Qi7Xh4FlBfM8cI5aAwPk\n" +
                        "qucI8HhE1OaK5NzGOEvUheQ8O+Wa0UMGgH0dgks62pTSg6X5VeDT5NK7LiQ6O/hCYEcZrPO7D7i2\n" +
                        "2GuOXz5byAVw3wmSi4Jd13HSTPKdCF4dEXPbPRuzG2jpDkaEABFxUP0acEux9ZHfwGxltEPwobqu\n" +
                        "/wOMppT2mq8A2+q6nk9+ddNJAoZSSj9vKiNis7qrruu1heDRWRAbTCmNkTfUVFFPdJ2clJPy6pZS\n" +
                        "SLymtM9Uz1ejoVuoXqeubvVbqV7V3GzqUvWyUphcoS4t+v6SydIrIfht9e/mi/0u9b3qlUU3R/2w\n" +
                        "+hP111VVbVEXqDcV7H0l3S0wXwn2qb9T/1RsI+ob1VUFf9bMjKYSnKN+x1xUbii6T5gL0SF1WSHU\n" +
                        "uQCtKYOtAqjreky9Qb1NvVs9V91v/qJwS1VVG8w3xwl1UTcOM4W1j8nPZuPmaldyLl4BPFKef1Xw\n" +
                        "Q8A4MKAOR8REsU8A+4EKMCJqcjocJKe1RI8c3e3rVlNuJn/JOg/YBjxJLpUOkkuo3wLvBGp1W0Rs\n" +
                        "L9hbgQPkT3PfBe4gV+BzmSzlXkopjZJzdM/aciaCTwEPRsQe9UfAIXKRsDEinlK/UNf1eqAvpfT1\n" +
                        "koM313W9JKW0CNgUERPqbeTabj+wqWyee8j1317gm2VCJ+XVJ/8DZ2WR61rFkqUAAAAASUVORK5C\n" +
                        "YII=").bitmap,
                iconColor = 0xfff03c51.toInt(),
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "网易云音乐",
                packageName = "com.netease.cloudmusic",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAR2SURBVGiBxZlbqBVVGMf/KyQykexUpvmQoRhJiaAnjSA2hSdQo6fzUhYeMukhQRDC\n" +
                        "8OGcBw2ySEuIohuGlRUUhR2okKNJZhcp0YoyM0mTiOxKN4NfD7N3jHO+b83Mntn7/GEeZs13+82s\n" +
                        "WbPWmqAxEtAnaZ6kPkm7JR2QtDuEcGqsaiol4EbgdWz9CGwDbhjrOqMCnnQALD041vWaAjaUgGjp\n" +
                        "oW4XOQ4YAgaBi4zrd7QB0dK0bgBMBNYAf6cSfwScl7E7UAGk0UmAycC9JC+npQdSthMiRfY3beYA\n" +
                        "9xvX93USYgVwPOcuDqfsex2b1UbsBvAMcBQYAWZ3CsK6a5aWpnzmODad6zI5EC8XAPgeWJHxu9ix\n" +
                        "XTUWEDsLQGwGLnP8Lb3YbYiBHIDngQU5MQ4ZfieAC7oFMSMHor9gnJcc/7XALcACoKeTILEuVRSi\n" +
                        "QTICFdFzwJK6IQarQAD9JQCy2gPcVgfEknYhgNnAh20CZLUZOKcKyFYn8KYCENaLXUV7aOfDCEwB\n" +
                        "/jICfgdc2mWIlg6VhiEZSSzdE/GZWgDiceA1o/0DYB2wt1YY7JnqfuDsiM9QpIC9wLymnTedn968\n" +
                        "vioHZtirIVvQQifAlpynccLxGzRsLQ2kbCYBH0dgbi0Ccp/jvDzi4z0Nc2AAvjJsnzXsPnfi5k/t\n" +
                        "SabQlqZHfN51fGY59psM228Nu1nAr07s2/NA3jScjub4fGb4jETsFzvFXWnY3uXY7szanpU5n2rk\n" +
                        "/iYGImlSzvWs3nbarS2grZK+NNqvBxalG7Igl5QsSpLOL2McQjgtaZdxaa5h+6ekUe9PU5enT7Ig\n" +
                        "Ze+uJP3ehs9+o22yY/uW037GO5gF+a1sRZK+MNoagNVNW7LWIVYcye/aM9MndYAcdtpXWo0kX2dr\n" +
                        "OP/asg8h/CDJ2g8enz7Jgljd5BorQUreHRti9AdxqaRPS8aRpFHDc1TA+85w58IAPcBhxw+Sva+R\n" +
                        "HJsdOXVZX/ozhvjsEznmxFroJWn+BngkUkePpIYyfTqlfyVtiPhLkvVBPpk+yYK84gSKdq8QwhZJ\n" +
                        "23KK8bQ+hPCed7E5aFijqTXy/e80xXn0fwAz8irCnn7EdHeBmNc6vovyHHc4juvykjb9h/AnfC0d\n" +
                        "AZYVjLfRiRHfpQeWOY4HiyROxbkJ2E7yoreOx4DFJWJMINkHzsqatoxyHhe5o0+Xgakq/IXWo0UD\n" +
                        "xFZ8aztcf6uGmcBJI/8vwBVlAsX2pK7uIEMrv7c7ub5soEYEBPJGjQqKQOwDJrYTMLbbCKm1dk0A\n" +
                        "c4FXnVynqfJPheS/d0wvYKzu2sizGvgpkmdN1Rx57wsk86mNQG8bsVeS/ECN6YnKEKmERX/2vwEs\n" +
                        "B64Cxjux+kg27I4UiOeu/6vAxIZlT8dInuhBkm3Xf0r4PlU7RAomry/XpTs7BpGCiY0uVbWdun/2\n" +
                        "FAAawN4LK6tTwMPA/HZrCTUBXSfpZknzJfUqs552dFzSO81jOIRQbjmbUS0gWZHsvk+TdGHzODd1\n" +
                        "+WdJu0IIn9SZ8z8bhZBzKTUnnwAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffbd0020.toInt(),
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "什么值得买",
                packageName = "com.smzdm.client.android",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAhNSURBVFiF7ZdrjJRXGcf/573MZS+zO9wXdhEosNClJaJtKdpatTax1tRLYoypiR9q\n" +
                        "UIz1Ev0gMdVIbFS0qTGaSGJq0kojaLRFGmNI1wLhUmBhgNmZ3WF3dne6c7/P7Mx7Oec8ftidF7bA\n" +
                        "7rBg+NL/p5lznvO8v/Oc5zkX4H3dntidckSxmFeq7PvM436AmcZ5KPQqW7565E75v21RanwdmWaC\n" +
                        "GjLrWT4y8tTd5polmU78zQGUkkS18pfb9anM1UnhQK9Ixf8oUvHfWGORbfMClssHZzXUqhdn+Usm\n" +
                        "W6lUWk/p9IoF0V4HOH7lORLCCQpFI9+d0z4cWEuWWSAiItuyaDj4EADQSHC1iMd/LCulAbKsMnG7\n" +
                        "wiPhZ5phmDOC0N25WQBLu3bR2bMdN7VXW+IkxTgAQFJZMHWFyKX2YuW680pX1x7W5vsgdL0dqtYG\n" +
                        "l/uRZgC1OXtJeGb9l9zAh/5VmWOEjymaCwDgcnWq92w8AMAFNrNZSAmq11Nkm//mdm3vbQNKzb34\n" +
                        "2hBTIvGa4vupdP7392vYvHkTXOpWWTd3kK/9cabrG2e6VTCmQnCbqtULxO0TJPhRdTJ5TNm2LdMM\n" +
                        "3LwS+fTvnPyz7Ty983YPANiR0GNyauog1WtxkpJuJFmvT4jx6HcoObGFgkHXHQF6r2Sx0N/4oMgk\n" +
                        "9zvgY6M/kJZVISEMaZlZUSqcFZXSS5RK7KJ6PUZEJHKpff8XqIYocHqtrJbjDUAeuvSF2f2BVkpE\n" +
                        "llI43H5tm5yaGiciEsnkHgAgIoWnE58WudQ+mUsfFrnc8xQOrL1tQD4y/JXG8slaJUxHjy6dd1LR\n" +
                        "6CbivD4dwcx+kZz8tpiqDpKYnQayWBiYczdoRiI+8aKzvNHIL5oZQ6OjD5KU1mwaSdflqW1nKZe7\n" +
                        "txmfN6xiCgYXwdf5KQCAZRV4pXTTI0tEh3cyX+cXScgh8np3MMZ0SMmpWLxAJI4oinZYlPJLlFU9\n" +
                        "LzPd1QkAZJplNj6+8ErmwfNPO7Ot19+lK5d6bmYr04lDTrCM+qSYjD1Pmcne6+2Sbzh25dKJBcMB\n" +
                        "gMymD127IrJUPEwHDqg3srXOndru5N3Y6Lca7RSNdlqDg9vNkZH7AMAeDv/M8VepDBDRwq569uDF\n" +
                        "R0lK/t59zb5w9uM3siciJouF/xARiWzmVZ5IPCnyuddkvVYmIpK2Xa/296+wz537hANYLY9R8J2F\n" +
                        "XRhkNvN6Y7lkqXiiAcuHQl+/2RgxGfvV1YKYvlxIziMik9nLx8Y+AwBmILCFZCMVjFIzt6PrxIcG\n" +
                        "n3QqNzL4TStwdhtJaRMRiXTy507UMpl2ikTczrjRyLPTfIJEPvt7a2xsG1H/rAKkQKBb1msTDf98\n" +
                        "aPDztwRHRIos5I7MJPEbAEDHj68k08wREYlc9s8AIOLx3WTUJ2WlfMK+OPAxALCDgUdmtg/TvHRp\n" +
                        "s+Ozv99TD51fc9V//urJNBrZ3QyXcxcQI8NfZZ2LPkm1qSCLhnYBAPz+rLSsKAAwt3u6khW6B27P\n" +
                        "StbW/rC6Zt1vAUCr2yESogJNc2le91N2JPSwKBb+RNu3R90b74/yROIPjDFJpjHkwHta+pqOnnHs\n" +
                        "yDpZq02Q4GSHL3/k2j6Zz87kpBGlYNBFJ054yTDCjUiY505uBgBZrVxw8tCpBkGyXA6JWOw5ABDj\n" +
                        "0R85S1zMn6R4vKUpQJFNv0xSkh269MQN+qZvNNyeMgOBLQAgrgzvbIDwSOiz0xPJ/6NR8LKQf51n\n" +
                        "s1+jkZHV1/ri8dgzThHVaimKRtfMx6YBgCzmX+GFzK/dm+8LXmdhWSkAAFO8SmtrN4DLIlc4pawR\n" +
                        "JlTNzdye9QAgpqpXNL8fVKkEFf+ip691YYyGe/X2xY+x1tYvgQAwgLlcy2zO/QDG5gXU129666YW\n" +
                        "pYJE1yoAYKxlOg/1FkqSkHmmoguelvUAQOViEOgBNG05DQ0twdKltqzVvsx8vs+x1rYnoDAFRFf9\n" +
                        "qiqYUV0yd/zmu/IDQEvb9LtEUcA83nUAAM/iIllmmrlcXczlXgMAzK0PQwpiXu8K6u5+E6q6XvH7\n" +
                        "/QBAnJdQqpwm4BA472B+/0/AecGu2+HGZygcbrfb2vqgAXquNMD6+qymAEnXrh7qTOkBALZhgymL\n" +
                        "+UkAW0lBNwBoekuIDDPGWlpWM6/3AbKtizKT2Ue51HG13X+SdXc7DzBzfPyfrFp1qX6/TxRy32Oa\n" +
                        "/lFS1Pt1XesGmCpbO8I8Fvul1tMz/7uanznlbN68XDrWaBeZzCsz1Z2ZGji+EgBEMr6fpCRreHjn\n" +
                        "dRONxbzWxMiHeWToWZFOvyDL5beIc+tmTwaScri5CHLbavxWVK2TiBhjjACeAACmaR266l0NIK6M\n" +
                        "TXzDlnjRtXHjWYpGPWhz90nmeghSPkqtrTt0j6cHyo1fuiQlkWkWwHmSEZlUrx9sDnDJihxIEpjC\n" +
                        "wNCBwcHlAJLQ3cUZC11ZtrybiBQ7FLpXUdUNolb7oQQ9yFzuDyiqOuvWQtyegs1j4PaYNMwrzDCi\n" +
                        "3DDGGedR3e/Pg/MyyuWq0mwO6h6lCptX4HL5oKrtVnv7EgBJcC5nwgqlY9ELZJp79N7eTbMiRBJk\n" +
                        "mmlY5mni4jQVi5d4uRx2MzbJtm6dmu/bTQECLiLLFMzlAggdis4WT7cr00cVY2Be7wbHXEqStdqA\n" +
                        "NIy3mZTH1Gz2KOvryzcDszDAWq0Iny8JwM/cbsYsuQwApGFknVhJCbKMy6JY/DvVzTd1IQLqhg3m\n" +
                        "QqFuCZD19mZFOtXP4Js+cxV0AYBGtFtmsxPQlFUylTmiud3/1deuNe4E1C2LTp9eLMrli0REdvzd\n" +
                        "l+4KxHwyz5zZIkqlv9rDw4/fbZb3dSv6H00jWCyac/vVAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffcd3935.toInt(),
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "王者荣耀",
                packageName = "com.tencent.tmgp.sgame",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAUsSURBVGiBxZptiBdVFMaf+0+2LFhjS1M0i80QXyoziugNCXMNNwtrrRAhkmwjJIKy\n" +
                        "PiTrF9OKEoOIbMGioCVjczdxLQz9oGb0YrWbFbsbvkUvliKGZh/69WFmbLp778zc8e/uA8P859xz\n" +
                        "zn2evffOnTmzRlUCMEnSZElTJV0jabSkEanjL0l7U8dOSZuNMceqxaE0gOuAZ4HvKI93gUeAi4ZC\n" +
                        "wCJgy2mQd2Ef8CRw7mAIaAA2V1mAjW6gOYDTa6EiWs+wABttQH0Op13AgyEilgyyiAT9wL0eTt2x\n" +
                        "zxRJqhTUMrKw6uqiXlIb0JIYgLOAPkV3xwPGmG8LZQJeGqLRSONrYCJQC+xN2TcmPIfliHhVUuGF\n" +
                        "d4awXdJ9kpC0R9LYVFt38mPA1IqVAyzX0IvokjRX0nmSvtD/RUgpIQMAvF2l6XAS2A98BnwTX4eg\n" +
                        "DagBrgYOeXyu8ImYV5Z1jC7gCWC6J/9EYC6wFNidkac19r8ZOOJzyhqNHSUFdAKNWXPE0VcN8DjQ\n" +
                        "a+VaE7fPBo5ldepLvLikiBZnwoH5J3jsdcAy4CdgZWxrBI7ndezr6PMSIhYUERHn/zWn/dr4fBfw\n" +
                        "d8H+x9pJyozGvIICJsZngKYc33uAfwI4PGcn2BQoYnUGmVqip+NWYDswI7Yvj2NXAdM8sWOJNr+i\n" +
                        "OA6MSYLrAkX8CIzyEGkAvrf8bSEJ1uO4uwELA/m0JIELAgMf84ho8fj7hAAcAGY6cm0M4LMrCXo5\n" +
                        "IKjXI+LKjJgsIRBtlI1WvpkBnAAmVCQ5b4sedDlEDJe0JiCHjRpJi9MGY8wWSa0BOW6vSLo8IOBN\n" +
                        "h61Z0gyP/6GCee+wR0XSrsKspGnKG7MUnNUOYKvH/xXgbMt3RoZ/p+V7UwC37SFC+hwixnh8/yCa\n" +
                        "ci7hcz0x3ZbfiABuh4q+IUqSa2ce5/FtN8accDUYYzol/eZoGmX5HZW0ryC3C0OEjHbYDnt8x+fk\n" +
                        "Mg6ba286mJPnFCoZZGzUA7WW7YjHdxaeCgjRo42rBrDNYXNuvC5UJDn3Bg8mpS+MMYcVvbm58DHw\n" +
                        "QNoALJK0zuO/w2ErXPSoSBqwiDMwyWFb6/G9VNI6/tsQn1G0N9ijmuDL9AUwUtL5BXn1hY7IDbbB\n" +
                        "GLNW/lFJ42hG23pjTLtly3xSttBbkdQREPAQcKPDvkLSnzmxvvaDxpj5DvstAbz6KsaYryQVK3JF\n" +
                        "eNQ2GGPelzRHUn9GnOt2vM0Yc7FtBG6T5KwwevBpErg6YPMBaHBlA6YCLxK9tiZI1sidVo4lPlbA\n" +
                        "R4F86pLA6wMDO30k4nx1RN880kJmAc8DdwO+jVREVZgQbLITvBOYIHcxAh2JkCKIxYai2U5yWYgI\n" +
                        "YFhBMYWEEP6CB9GXsnNcyYqKGA60x9fLSOZoSSH43y7z8LQvYRERtcAHlr2XqNhWU+Svn+qvkai4\n" +
                        "Vwb9gOv5L1dIE3AB8GGGz27gKaIFPdnTx3SiBd1VUsApPj4Rk3NEjMb/UuTDSaCHqJC9n/BCtg+r\n" +
                        "soa5yRPUBIwHdlaJxOmgB9jq4p/+0OOaCvMVPcy1K/ongKHEUmPMC77GtJApVtt8ST2SNij6XjeU\n" +
                        "eDh+OM0HsCc1hE0pu68eNVi4v7Bcoi+lCQbcDYjWyQ9Do6P4k4GAaT4RKZ9xwBuDLOJ1kiJ1QSEL\n" +
                        "s0RYvs3A72dYQAdwa2EBKXIhb2MCrgJWUv3p9gkha6KaAOYAbwEnSpLvAVZQZgRScNWXSgOYrajo\n" +
                        "cEl8To46Sb9I+jk+J8d7xpieavT9LwbxY+J62p7dAAAAAElFTkSuQmCC").bitmap,
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "大众点评",
                packageName = "com.dianping.v1",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPqSURBVGiB7ZpbqFVFGMf/nxRdrAjsEL6UWnCyQBJNSwKjmz5kaXF8KAkM7P4i6oMR\n" +
                        "eOzBMD0EvRURZEFhF0JMSMsiCrXbgTJTktMFNEyPJCaEPvx8WHvnYp21Zs3MmrXpoT9s2HvNN9/3\n" +
                        "/637zGzpf6UVMKdJ//MSGLhK0hxJEyWdkXTAzHZ49LtQ0u25j0ma3tRPsIApwBuU6wiwytF3R0mf\n" +
                        "00BfLxkEzAdOVEDktaWi/8sV8bNjPY2LgOiX9K6ky0qaRyTtlPSFpFFJC4DVJXF/VKS/KNRPVzHX\n" +
                        "yHOSxne+H5f0s6TTkvokXSdpSiF+HfCZme2KNemjIBDgBkmLJX0s6RJlxn1Oh+WS/jsgyoxL0p2B\n" +
                        "/QYKv6+tiPs1MO+/GgMCXC9poaTJZras0DwptlBBN5VsO2Vm0SACJgBLgfeBM4W7yMRC7DKPO1Wp\n" +
                        "cjnurgj5KhpC0jgzG5V0v6RFGnuEHiv8PhRZ57vc9yUVMV9G5j4nYGXFXjpUclS+iTggGzp9b3HE\n" +
                        "LEwB0u8oMFiIfTQCZH6n7/6K9t+AyxuDdIqsD4DZFAAxAIwH9jpink4C0TF3hWOPAQwBF+Tih2oA\n" +
                        "9ncg7qiB2J4MImfukRpznwP35OIfAt4ERgtx64EZwMaafADtvPECH3gU3wI8DtxY6DuD7Bp6G/jH\n" +
                        "I89drUDkDPVCg/VO0sBUvWqn0DM9gcjBrAFOJQTYC9zbU4gczHTgrQQQr7bp0wKA5kl6WNKDiWof\n" +
                        "VjYQ6342m9lPscm8QboCZiqbJJgqqV/SxYWQ22LNSNojaaukD81suEGecAF9wDr8bre++ht4Fmg8\n" +
                        "y+ML8SRwMCFAUbuA+9qGeKFFgKJWtAWxrYcQXTkfoDEXO/VRrWmlmQ2VNYTOonwaWHhE0teSDkr6\n" +
                        "QdKRXNskSXOV3eV85wI2Ar+b2TuBPs4JGAw4DTYBDwDne+YeADYH5C/OynhD3OpZYJgGQ1ZgHrDP\n" +
                        "o84xYGpMga0eyV8nwXAVuBp4z6deaOInPJKWXoANgXxOtcW+ySaQTQy49GJqiACY3b6JltYkGibV\n" +
                        "7Ed5/X7gaI2HWVL9ssKCmva1ZvZXGttjZWYHJG2oCXPPQwPXMHYKNa/SRZw2BIw4fOyU3EfkZrkf\n" +
                        "mK8kdevWJyXbPpL0lLIxUrWA5x174Zc23Dq8TCNbl3wJmFsW49rjMx1t+5pZC5OZfS/pSleM69Sa\n" +
                        "5Wj7McpRi3KBlC12dnU4tZGmCl7V7ejPpC4SKBbkaFIXCRQLMjmpiwRygbiWwnr/n5EauUD2VGw/\n" +
                        "qYghcttyPUe6D72Tkr5VdoReM7OR1l1FqHLPki3oXCppm5md6J2lOJ0FIHdvO6S6ZJcAAAAASUVO\n" +
                        "RK5CYII=").bitmap,
                iconColor = 0xffe19250.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米视频",
                packageName = "com.miui.video",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAC0AAAAtCAYAAAA6GuKaAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAHiSURBVFiF7Zm/axRBAEbfdwQrO4uQJq2k90dMkSJICkmjSAoLCTGYlCks0iWpxX8g\n" +
                        "IiKIjShY2AhimxQpNIKQIFwIBjSp7JLmS7HesYTL3oadnbngvWp2Z/n27TAzuzsDFxCdVWF7EBiJ\n" +
                        "6JKnKalZ+mrbi7a/Oj3fbC+VEX6T2rQDn4qEp1LbFXA379rIlR+X7kPxeWr7UuugPRBtHwJXkiiV\n" +
                        "Y1jSHvxradsj9LYwwGCr0Dh9ooe53Co0iq7qVfrSsehLx6Kq9CrwKoTIeagivSppRdIMcBV4G8ip\n" +
                        "K0G6h6RtSdPANeBjiMwigvZpSZuSpoDbwJeQ2XlqGYiSPkuaAO4DG6Hza509JL2TNArMAVuhcqNM\n" +
                        "eZJeANeBJ8Bu1bxo87SkI0nPgJtUnGmiv1wk/Qb+VsmIKm37ju0PwKMqOQOBfAqxPQnMA/dC5NUq\n" +
                        "bXuCTHY6ZG4t0rbHyWQf1JEfVNr2GJnsw5C5pwkibfsGmexsiLxuVJFett0EbhF5zaRqS78MYnFO\n" +
                        "/ss/lyT0pWNxoaV/JbUoR/vLsAEgaQc4SKZTjj+tQr57rCcQKcs+Z0ivxXcpzXNJxx1rbL9Os6VS\n" +
                        "yPuuj2R7wfb31Ka2f9ie6eRYtPk5RLbclYI9ST8T3btPmxN1pTrd8xQi1gAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "腾讯视频",
                packageName = "com.tencent.qqlive",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADYAAAAyCAYAAAAX1CjLAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAQ/SURBVGiBzZpriBVlGMf/78miwNboAm7ZDcNgLbpiZUSxWlJKHwIpukjEQlEsfchI\n" +
                        "InITxND8IiWCLQuRHzS7X5QwUqnsstJSlomxom5E0QrWdtkgfn2YOTmdnZln3nfmNP1h2Q/nef7P\n" +
                        "85t5z7yXOU4BAi6TNFfSLZKukLRb0h5JO5xzm0M8axfwKPAL2RoClgDn191rYQHrc4BaNQb0AzfW\n" +
                        "3XeugG4PqFbtAO4HjqubY4KA10uANTUE9AIn1c0jSQJuqAAqqb3AI8Ap7erZpUD0SeqSdI2kDkmH\n" +
                        "JR2VtEVSj6RzK6w/LOl5Sf3OuR8r9D0mYBrwdsYVHkjEXQQ8AXxZ4R08DPQBJ7QD7JWcwn0ZOXOB\n" +
                        "tcCRigB3AbdWCTXbKHivkX8WsBgYrAhwLTCtCrCVRqGbPLxuAzZVADcM9JQFe8socnGA56XA08B3\n" +
                        "JQE3A5eHgg0b5qcFGUfencDSkoBjwOO+hc+wXEOh2gC4FZhZtKA1+R6oAqxCwBHg9iKFHjSMPjLy\n" +
                        "V8VxqVNCAcCjgYDLrALPGQbvGvmjidg3gBmegFcSDbEQvQxMzTJ+30h+02isVSPAXT5wsc/SQLhB\n" +
                        "4Oo0w4+NxNwdcU7e6gC4ecBnAXA/AXe0mg0ZSRsCwSAaDV57MKADWBMAB/BY0mifETyQ04cFBtHD\n" +
                        "odsHLva9E3t+TdP6psFBI3BdSbCmngqA6wL2BMB1NyRZu9lx34Yy9CSw3SfBOfe1pPsCaj3ckHSi\n" +
                        "EVQVmCRdD34rGefcp5Le86wz/b+8Y/8IWOWZ8oFnfGejQFA7TpaKrffC9VdD0u9GUEcbCq/0jJ/n\n" +
                        "Gf9SQ9IfRtDJnqaWep1zhR8iwEJJE1cW+do5SfYdqwrsV0kznXMHiybEUJs862xxzm0sMkFvM4oX\n" +
                        "Ue7qJcVzKvBswPxF0sRaUn1SEmyRJ9Q92Bc7TdtajaxF8N5AsAPALA+gGcALAUAAS9IMrW3LSABY\n" +
                        "P1D4uwk8BHwfCDU/y9RaSf/sCbbYA2gO4S87Pifv9AzosRyM5j6Mw/YDCwoCXUJ0V0O1AZhiFbm2\n" +
                        "gNHZOfkLiHa/nQWAziQ6a/ytBNSKIhdPwOkFzGYXMsuucTzRa6NDJYAG8T3XJxpGebKPu9J9pxC9\n" +
                        "6NtdAgiiI/jJPrUnxf+/knRBTtw5nkDTJS2K/87zyW3RLknLnHNbfRObYPuMuMzvWFLAVToG5HWF\n" +
                        "U7RcEdSfwQ5EL/PGcobCq0b+fGBjyeHW1HYCzkjymlueU2wwJf46YDXwTUVA+4HeyoBaml2RUfSH\n" +
                        "+PMLiV6pWutLH40STRentgUqAXc38E6i8DjwLbCzQpim1gB5D622AE4GZhHP8IQdg2XpRaIHTf0i\n" +
                        "GoJl9Rpwc90s/xLR4WWZOzSnboZMAQs9YMaBdfxfhpylGC7vxyujwDNAV519TvjJUVEBD0hKriGP\n" +
                        "SPpC0oBz7lDZxsrqb3F98Nv3RSEjAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffe06639.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "QQ音乐",
                packageName = "com.tencent.qqmusic",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAOBSURBVGiB7ZpdqE1ZAMf/azCahrxwY0LqUpgZ6YrCPJBIRJTrwQMejY/y5oUi4/q+\n" +
                        "zMPUECEe6FKUPJB8PLjiwc3wIpOP8T0vY540pZ+Hs7e2ffbeZ33d+zLnX7tz1mqt/8fae6+z1t5H\n" +
                        "aqKJ/weA6T79+veCkR8kTZQ0OnMg6amkZ8lnjzHmaQnFEkndrrpRggAzJC1Kju8t+/wp6Yak68aY\n" +
                        "k0ldP0njfDwEBQHmSFonaalH91ZJryTdytStlGcQLwDTgBP44QFwBFhSwHs5adPq6sn5jACrJB2v\n" +
                        "aPKvpL8kvcgcabnHGPO2hHe9pLlJcbWkLa7erAFsKBnlrYG8rcCzHOeBWL7zYpcqLpfQIBdKeI/E\n" +
                        "8p8KnW1w3T8EhnhyH2rAfSZWiK0NhFJs8+D+xZJ7fWiIxZZCAB+BHx24bzlwvwHGhwS55iAGcMyC\n" +
                        "sz0x5opTviHWeogBLKzg3OTJmWK1a4hhwGNPsZsFfMuBm4EhAK64Bvk5UHBNjq/RrOeCKUWevyrJ\n" +
                        "MtspeT1+BYZmyrsC+bJYZtUKGBxp5HbmeDsj8f5jG6Q9kiBAW4Z3OH6zVRHm5X0XXVptBXW+2Jd+\n" +
                        "Mca8UbxL7Lt8RVEQr6VGCWaTWa4bYw5KuhuBd0S+oreDSFL+h2xPBM4+PyOS9C2wPS0YY85KOhfI\n" +
                        "aXVGBgaKFGEzkB3F/YF8dYNdFMRuenPH53WYMaZb0sEArvf5ir4MMg+YnykfkFS47bVAXZCiPXto\n" +
                        "kHuSrqs2O70ua2SMeQ7sk7TXQ6MuSB3wX/UC7AAG2boB+gN3PHQab62BUZ4h2m0D5PRWeGhNsCV3\n" +
                        "3VB5hcjonXfQul3EUbb6vezgo8sY0+Vu/wu4PPq5aN0SaAM+WI7QLHffhZoXLbQ+kFmI2hJ32KSI\n" +
                        "ESLR+81CrsOH2Gq7GzFIo8dOj4FhZf3L7hEZY/6W1BnLaAR0Jp78AHQ1GKlY90jVc67QyeSzyMsK\n" +
                        "keBlOTClgv9ljAyp0LgKofvAmED+qgfkcV/8AGMrxK4CX3vyHq3gHRs1REa0pWL0zgMzHbimAt0l\n" +
                        "XJeAll4JkTEwEPid8h/M3cBkYECu3zfAJGqL0qol0GGgNzZ3pYEmAHuAdyWG/gP+SEy/qDCe4jTw\n" +
                        "U58FKAg0EthI7T5xxRPgGLAg1IeJESYFMFK1F/75PwxI0vPM8Ui19+s9MfWbaKKJcnwCIC7Ye698\n" +
                        "lvkAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff29cbb6.toInt(),
                contributorName = "fankes、naicfeng"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米音乐",
                packageName = "com.miui.player",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAOBSURBVGiB7ZpdqE1ZAMf/azCahrxwY0LqUpgZ6YrCPJBIRJTrwQMejY/y5oUi4/q+\n" +
                        "zMPUECEe6FKUPJB8PLjiwc3wIpOP8T0vY540pZ+Hs7e2ffbeZ33d+zLnX7tz1mqt/8fae6+z1t5H\n" +
                        "aqKJ/weA6T79+veCkR8kTZQ0OnMg6amkZ8lnjzHmaQnFEkndrrpRggAzJC1Kju8t+/wp6Yak68aY\n" +
                        "k0ldP0njfDwEBQHmSFonaalH91ZJryTdytStlGcQLwDTgBP44QFwBFhSwHs5adPq6sn5jACrJB2v\n" +
                        "aPKvpL8kvcgcabnHGPO2hHe9pLlJcbWkLa7erAFsKBnlrYG8rcCzHOeBWL7zYpcqLpfQIBdKeI/E\n" +
                        "8p8KnW1w3T8EhnhyH2rAfSZWiK0NhFJs8+D+xZJ7fWiIxZZCAB+BHx24bzlwvwHGhwS55iAGcMyC\n" +
                        "sz0x5opTviHWeogBLKzg3OTJmWK1a4hhwGNPsZsFfMuBm4EhAK64Bvk5UHBNjq/RrOeCKUWevyrJ\n" +
                        "MtspeT1+BYZmyrsC+bJYZtUKGBxp5HbmeDsj8f5jG6Q9kiBAW4Z3OH6zVRHm5X0XXVptBXW+2Jd+\n" +
                        "Mca8UbxL7Lt8RVEQr6VGCWaTWa4bYw5KuhuBd0S+oreDSFL+h2xPBM4+PyOS9C2wPS0YY85KOhfI\n" +
                        "aXVGBgaKFGEzkB3F/YF8dYNdFMRuenPH53WYMaZb0sEArvf5ir4MMg+YnykfkFS47bVAXZCiPXto\n" +
                        "kHuSrqs2O70ua2SMeQ7sk7TXQ6MuSB3wX/UC7AAG2boB+gN3PHQab62BUZ4h2m0D5PRWeGhNsCV3\n" +
                        "3VB5hcjonXfQul3EUbb6vezgo8sY0+Vu/wu4PPq5aN0SaAM+WI7QLHffhZoXLbQ+kFmI2hJ32KSI\n" +
                        "ESLR+81CrsOH2Gq7GzFIo8dOj4FhZf3L7hEZY/6W1BnLaAR0Jp78AHQ1GKlY90jVc67QyeSzyMsK\n" +
                        "keBlOTClgv9ljAyp0LgKofvAmED+qgfkcV/8AGMrxK4CX3vyHq3gHRs1REa0pWL0zgMzHbimAt0l\n" +
                        "XJeAll4JkTEwEPid8h/M3cBkYECu3zfAJGqL0qol0GGgNzZ3pYEmAHuAdyWG/gP+SEy/qDCe4jTw\n" +
                        "U58FKAg0EthI7T5xxRPgGLAg1IeJESYFMFK1F/75PwxI0vPM8Ui19+s9MfWbaKKJcnwCIC7Ye698\n" +
                        "lvkAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff29cbb6.toInt(),
                contributorName = "naicfeng、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "酷狗音乐",
                packageName = "com.kugou.android",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAVYSURBVGiB3ZpdiFZFHMaf2YhCLTJ2tzZXK/vQltqCMktECgrSQKTavi5E6EJIqRUX\n" +
                        "uwhZow/KTEnQi6IsoiINvUnrIsubFKyMsDaizPVjMX1ZFV2jLPx1cebI7Hlnzpnzvq9SPnA4s/P/\n" +
                        "fGZmzzvnP8coAkCXpEmSWiS12nt6jYzxEYETkirOdcjevzbGrCsyNiEBMEPSA/Ya3ZBUa8cRSesl\n" +
                        "rTfGbIq2Ahby38VCX85VMwJslXRnrUN3lrDNGDPF7RhGBPhV0jV1BPhJ0k5Je53rb0nNSv6f3HuH\n" +
                        "pOvqiLXLGHNtVS+wqsapHgBeBSaVzQS4F9hQxzJblXU4vQYnR4BuIPjUAiYAtwFjgPNy9G4Glluf\n" +
                        "ZTHddbSypPEG4JZMMq3AbGAF8CVw1GN3CPge+AiY5SHUDqwpmctK10GlhGG3J4Gngd9KJgCwH3gd\n" +
                        "mJbx93wJH5XUaGYJo65MwMeAbSXs87As47uzhO1MAUsjlbucICOBdWUzjcBG4CInTkuk3dImJY/D\n" +
                        "IjycbhOANkmbJT0UYVcWMyQdwz4BjTEVSXdH2LXIjkIeFqfawOTaB7s0Op24TxbobhSwPUfhM8fZ\n" +
                        "mIalGIdBktlP46/N0d2et7SGJC12/v48anE0DpdKWgucb/9ekaPbImAowHJBqgW8VTB6B3zec/S9\n" +
                        "gwIs8eguceTLA/6GQsEGgUus8V0FJAB2eJIa2wASkGyB2qxOO4Ff/6bAVH1qjDlq210BHRe/e/rG\n" +
                        "evreN8bc4yMhqTfg+wpJcyXJGLNf0hdercAozLKyFpJtRRHe9vh9JKOzrDq6BPRG+HdnxfsE8xEZ\n" +
                        "dIIUPfZSvOhJsMeRL8nK05mI9A/wqDO4VfAtra+cdqdH7kPe0nrOGFNFhPzl5MNE6fSP5Jas0Edk\n" +
                        "wGlfHRkkRKRRJCRLxOLnrLCIyPWRQXxELmggCWn46ognQvLCdFVkEN/vyMvZDmCOaiMhSTcAI2y7\n" +
                        "1IyMLxHENyN7PX0zSvj0ocPe/8gKfERG2Xuw5pXBcWPMkNsBXCzplEf3YKTPEI6FBD4il9v77kjn\n" +
                        "A56+sZIe9PR/qKSiWCv2hARBIsaY4/InmUWIyFyg2e00xmyVtCjCpw/9xpi/HP/D4CPS5rR/iQjg\n" +
                        "I9Ku5HH5bFZgjFkt6c0Iv1nsctoTssK8pSVJ/REBfEQus/duPNUSSU9J2h7h24W7rCZmhT4iU512\n" +
                        "rUTcd5wXsDvpFMaYPyUtkHQywn8K9yk4rUoa2Nd0WdmVwOGCPZCvPvVBRmdlVeBEb17xFguAfqDV\n" +
                        "2tzvUwgRWe8Ee60gyO2eBDd79HxLLOalDWCRo+8t7YaInATareFNwD85QcZ4ktvp0dtHZolZ3SZg\n" +
                        "R47/PmCU1R0HHAgRCb3qzneCrQhFCYzywYD6uwH9W4FTEXm8EtAZErA7INyFnRXr5LsQmTOIb7Cv\n" +
                        "GsCNwImA3u4mJed0PozX8CrKfQG9M4UfJc02xqRbnXmSRgR0KzEFOrdUOqWBo52HH4AOJ+6cAv2N\n" +
                        "Iq6MP9px2gEcrzPRPGRJTIqwWZO3tFwcTp0bY/okTbZT32h8q6TO3GdJjFTcDqBS9ljhidQSaCMp\n" +
                        "HgyUHXIP+oD5ODUE4uppKWamRmUOep5xh4L6CPUDi7C/E47Pl0r4qLiGZY/e3gDu8BDqBd4jOfzx\n" +
                        "1cP2AFuAd6xua8bHOOo8eqvlMBSbdPD8AmgmOYqYCFyYozcVWI3/3LEI07POaj2eBviY5JOPUgAe\n" +
                        "Bz6pI+7p4+lGfzBwSMl2e5+90vYxJTWy8faeXvV84zLsg4Fz5hOOqhcrq9Bz1lIqj54sCekc+swp\n" +
                        "qnbF/+DDs38BYWouQVD0KSoAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff5493ca.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "滴滴出行",
                packageName = "com.sdu.didi.psnger",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAJGSURBVGiB7Zm/a1NRGIbf7+qi2EGiQsHBSfDHIuggCFaQ4mDFwcUi/Q8cgoNrOzgp\n" +
                        "VvoPVARBii7tKGI2uygZRKG4FRIEnezS6XHIrYSYe+93c+/JTct9ICQk7/nO+95zcu45iVRTU5OG\n" +
                        "SRKwLmmuYi9JtMzsRpYoAlY0uSHcRJIeVm2iDKKqDZRFHWTSqINMGgcmyGGnbklSK6SRFAA+Zom8\n" +
                        "QWRmlQQBrkuayZC1DszUqoOMEfOI9kMQF3WQScMb5GhQF+mc9IgiSZ2yigXivEPTjSR1HcJTBc0U\n" +
                        "4YJD0/GOyKWCZopwzaFxj8g0kLVNKJ24z2mHtOMNIkl3Rrc0Mt4+uwJu42MbaAS13QfQiPvM4qfU\n" +
                        "W7U+O2uflrQQzPn/LMR9ZrHx7xXwyjkq7WC2BwDaTk9z/Y1mnY0A1sYQYs3p5cewxm9yhFkNGGI1\n" +
                        "h4/HwwpcBnZzFFkMEGIxR/+bwKGkQk9yFAJYLjHEcs6+76UVOw58y1mwDTQZYWmmt8Q28X+x93jt\n" +
                        "KX4/Z9E9toEVHDsAYCbWeu4Tg2wBFwdrDj1GAo8kPfNc1QS2JH2X9FvSr/i9E5Iaks5JOlug9lUz\n" +
                        "23Sr8S+B4+Rmkt/Ugz1AjisVmqaZvUj6MPWEaGam3hSpmqW0EG6AdxVOp3LvV8DzfR+iL8xd4P0Y\n" +
                        "ArwErgQJMRDoAfApQIC3QOZf0SECzdPb5O0UMP8VeArcKuLF9buqI9ARSbOS5iWdkTQl6Vj8PCVp\n" +
                        "V9KOpD99jw+SNszsSxkeampq0vkLMZsS+4iBecAAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xffeaa833.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "菜鸟",
                packageName = "com.cainiao.wireless",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMQSURBVGiB7ZjNbw1RGMaftwQbgtgghLQsmia+Fk2R+KhqGiI2Epp0IcRG01j42vUS\n" +
                        "kRARIWFB+AOENlIqlaYWGiE2bCTVtKlasEARH00kP4szGOPe3pm5d4bF/DZ38uac532f8zH3zJEy\n" +
                        "MjIyMjIy/m+AdmBzIFYF5JJINg2oBrYCh4AWoBaYXaJuJ45hYKYXW81vtpXHgRNuBUYozBlgbgzd\n" +
                        "vQGdpV580Bc7XA4DC4CeCQz4GQL2R9DeEejf7sVvBuKXy2GkO6QJP0XX9QQmLuXR6yvVxLkYJn6y\n" +
                        "poDm4oDuLdyeWw70F9C6UYqJ+hJM/GSLT68Rtx/eAB+B80AT0OA9f5pA54+lNTmil2bf831JNZLm\n" +
                        "RNToAk5LGpI0Ium9pBOSxiRVSjovqSqEzruIeR3AEuCrNxo5X/xpGWYpDi3++ioieGmWNM17flzy\n" +
                        "yJROT+QewL7AaPQB64ED6U8EAJ3BGq2IgfVye+B6ZPfJstbM+v2BvJsd9/5+LalNUnUKhUXhdNCE\n" +
                        "VPit9UrSMknzJB2ThKRGSXWJlReO62Z2JHRr3BHkibceHwKVXrwJePVvtgXDkSwDM4DegEgv3mkW\n" +
                        "WAl8S9nEWCQTXqEdBcQ6fW1OpmjiWRwTZ4uInvTaTcKdh5LmQRwTe0KKb/fazwJGk/MAcUzUAR9C\n" +
                        "6j8H5nv9FibmwtFcrPagkbsRE1z09W1IwICfK8C8MCbCLik/Y8BCn0ZrWUv/myHccWhFIR8G3JHU\n" +
                        "FGkKHW1mdsFnJiepPYZOVAbkDoxv/4iWMEodwQxArhzDH4NHFXLHkTh8DgbMLCdpg9xHV1qcMrPa\n" +
                        "CkmjMQVe5Aua2X0z2yB3RkuSbkn1ZnZU0q/bvDjsLJYJqAGOAwNlW0TuMmJXvmTVMcTuAVPDDh0w\n" +
                        "BXcD2QW8j5HvC3CNCf5XzEu0W9LVkHW9lLTJzPIurTCmJK2SVCtpndynwgxJ032/g3JLd1Buv902\n" +
                        "s/GwCQ6GGJlRoCGOgVQBNnpTGGTcMxr16ic18n6zA4skLfKFHpjZ93RKysjIyMjIyIjND4zSRJSu\n" +
                        "HGiOAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff4478ee.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "多看阅读",
                packageName = "com.duokan.reader",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMYSURBVGiB7ZrPq1RlGMc/z7gR8se2gshUyrJFdMlcRFx0YwsthBZhiEElIiX2D+RC\n" +
                        "ECJqU6AXFSIkcKUFZRCMkvYD0ZWLCqmNGWpBCtoPF58WM9Iw3jnnPe85d8696AfOZu7zPOf7mZn3\n" +
                        "nHdmbjCEugB4GXgaeApYOVzTEj8BZ4EfgKmI+G1kpbpLPe/s51f17VESW9tOl8HUsMSGthPVYDdA\n" +
                        "9EW+AtY28k4eP/8CT8xTXwV2tJ2mBvOAqx1gRdtJGuD5DjDRdooGWBnqNWBhQvGfwHHge+BC/9hE\n" +
                        "b209NGMRU0m8MnTVBwpm7Gz4StRVJweObllDisi5xCekyUt4d2h2qUinJN8/wOYUkYj4FNifUjsT\n" +
                        "lIm8FxFnU4dFxGv01tHYKRM5mDHzRE6QuhSKRMT5jJmz8hWZMxSKqE+OK0hdyl6RNRkzX88JUpcy\n" +
                        "kUo7YnUCeCk/Tj5lIuvUvRXmfVQnTB1SFvtWdVdRgbpIPUmbn++rbBvUyaHeJeoO9UrVPUjThGpF\n" +
                        "99+BK8AlYLKkdmzkiMxK7owb4lzirshsYy6IXEspKhP5JQpoIGQZr0TEYuBMaWXCvWZDQe+RGbzH\n" +
                        "He2f45GU4pS31vqCvx1K6M/l/YTz/0+C7CV1cUH/6TpP+wg+HJh/IqUhda/1ZoHIhHohN/E0XFaX\n" +
                        "9WevSm1KFbmsPlogs0a9UT3zbVxUnxuY+0VqY5Xdb+F6UDeq5yoGH+SU+vjAvN1VmquIqG4pkVlk\n" +
                        "7+e7qxVm/qF+4MA6VF+smKuyiOr2Ipl+kMfUd9TP+0Gn4zt1s7pwqLeyRK6I6p4ymaFwy9XV6gr1\n" +
                        "XnX+iLosiToiqofsfdlQG/U+9d0aWWqJ3GJfrpC6QN2u/lw3RJOfEKeAL4FjEXGjROBZYGP/GPm7\n" +
                        "SxVC/RF4uIlhff4Gvga+GXr8QWA5sBS4v8HzAT2Rj+n9y8acpgOcajtEA3wW6j3AMeCZttNk8hew\n" +
                        "thMR14G3gJstB8rljYj4tgMQEaeBbS0HymFvRBy47VF1nfpJ3Wv6GOhasu+7JbRaPdxy2Onoqi9M\n" +
                        "l/k/quraov1V/EcAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xfff28b3f.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "闲鱼",
                packageName = "com.taobao.idlefish",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPgSURBVGiB3VpLchoxEH3tYp+5QcabbMMNDCcIqcre3AD7BLYXWWWR8QnAJ4CcwHAC\n" +
                        "cJbZGE5gfIKXhTTQyBrNjOcDyauaoqSWNP2QutUtjaAmkIwBXADo2ie2j8baPiv7LERkXZcO7wbJ\n" +
                        "mOQNyWe+H88kR/aPaJ1Al+S4gvJZGLdCiGTUEAEfoagpEkOSLy2QSPFCclA3iTZmIQs/6yAQkXw8\n" +
                        "IokUj8xZahIiAeARxpWeAlYA+iKy9QnPAh2nOB0SgNFlmiX0EiE5BtBrSqMK6JFMCrWk8U6njjfe\n" +
                        "TBwSEYBnAM348PqwBXCu7cVdWglOnwRgdDxYYrsZIdkFsGxbo4o4T4NOPSNXR1KmCm4PSjRRbAgJ\n" +
                        "TaA4qWikW5KVvKHVdabGjLXw1nnh3HZIPVjPtuvpcs4LI5Ir9w+pQkKN3VNjXgFAx8ounbZzu/Ym\n" +
                        "NHuKxlpE5nkvE5EtyRmAz6p652VobPJDGQIisvBUjwAkHTs1eTlAF8AcZpOMaQK5WckXAsClmpVH\n" +
                        "lPSQJK9FJMHhZh2TjLM2wFvVOUXhEF71dZdskXHWOXJf/+EZisdT+t97BXAH4KnMP2r7fZUw4pAQ\n" +
                        "wDmAB2fcbhkiGkMRuUV5l70qYl8h2N184lR3OwA+5vTt+xRSvz55Lpjh+VKiJL95xL9F5I+n/iM8\n" +
                        "641UNlIFHhuZK5kvYdO2ufXIP1lZzxV0fAo4ylx4qp+se41w6F4BBL2WxsI+WfheYIwdconAuF0X\n" +
                        "fVvfhXGjLjIzz10DY2Mh+Y8Cuu0QyhD/KZwB2NQ01muZxgVsxIes0GjTgTmLzfNc9yJyZQfyLaVr\n" +
                        "EUmsIjcl+IRccRk3ve7AuFCfQWvMAOMaud+4d7BhQ/ryQkREJOi28+QOVmfY7wltoFSQGIAbo606\n" +
                        "KDeFVdEluUQg4CyACMDQqZt3RGRNcoN8O6kDrwDuYaJtvQTvYGw1wX7WfsGEIonSbWHLfexnZbO7\n" +
                        "Y/HswK73GDhlN9GKdFm1dcedKVmadL2ouiRtmDGG+17SJlZpYzfVXdq6kSpHNJc6JDnl4RXD2Jan\n" +
                        "tnxjy0tn3BerxBenfkSTSj976vQYY769mznMpVg9Hz8GdlHw/3ccJCIrvE1YThkP+iLVd2S6Rn3+\n" +
                        "vim8Aogzj0ytwPXRp4hh1j3JAajc4AnCezYWurGaIz8GaxsLEfFGwKF8ZIDypyRN4glGJy8yidg1\n" +
                        "2EM4HW0LCwC9QnYRAo9rM7WcF2syA/pPNprClnV/MKDIRGwnlJmwqU84HEJxQ4QmPNJXQjHJK+Yf\n" +
                        "PIewtmNUIpB7/lSGFIyX0x+eucnaBocfns2lpg/P/gI89K2h8DX3SQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffd6bc49.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "美团",
                packageName = "com.sankuai.meituan",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAJQSURBVGiB7Zi9axRBGId/I4IgFnZWIoqCoI2oUYKF1hZCIIiNrYqNf4BCAkIKBdNa\n" +
                        "KypY2Nmo2ChaRAhyhAhqQgolnDF3wgbCmX0sNifrZrM7+3WjMk91+/XO72GOeYeRPB6Px/O/AdwF\n" +
                        "2sAb4JzrPIUBDgMhG1kCTrjOZw3QSpGIc8t1RiuA5RwRgLOuc+YC3LQQWQP21TXmlroK9QGuSDok\n" +
                        "acFi7Fbd41cGWLSYhTRmXGf/DbBaUqJPF3gMDLkWqZM28ADY5UIkqBC8BTzZ5Nk3YLJMoEsVZH6U\n" +
                        "kHgV+34+4713wBhw0DZMvBuvAu+BkQIyu4ER4AbREnwROLr+bCIl4EzsW5ve0wPukdeDcoqEQAd4\n" +
                        "Bhy3lVuvuweY26Rm21IiyWh8DJMUKRJQUiipJymQtCxpSVJXUkfSd0nDkvZK2lGwrg0fjTEH+hdV\n" +
                        "RVxzxBgzLTXQ2QfMfP/HvyzyyBjT6V8kRcIBhynDF0njxpgL8ZtbEy+tyf0s9RQtFF1JU4oWkkDS\n" +
                        "iqRZSc+NMV+THyVFAkk7m825gUVFu+AXiv4uc5UrAm9LrOdFWQGmgfHKgTNErjYosABcbyx8ikza\n" +
                        "oUEVZoHLAxOIibyuIXwITFFgn9aEyJmKEi+BYWcCcYDPJSUeus7+B0Tb8KLccZ07FfIP2eL8dJ03\n" +
                        "q4uPSrIN6FwkE+B2gVm55jpvJth3+wA45ipn7gbRGHNS0n2LWtslnaqcqGmAyZwZ+QCcdp3TCqIj\n" +
                        "mSSfgDHX2QoTk3kKnAe2uc5UGmC/6wwej8fj+Sv4BUjRcEDGSSFRAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffecc34a.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "美团外卖",
                packageName = "com.sankuai.meituan.takeoutnew",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAJQSURBVGiB7Zi9axRBGId/I4IgFnZWIoqCoI2oUYKF1hZCIIiNrYqNf4BCAkIKBdNa\n" +
                        "KypY2Nmo2ChaRAhyhAhqQgolnDF3wgbCmX0sNifrZrM7+3WjMk91+/XO72GOeYeRPB6Px/O/AdwF\n" +
                        "2sAb4JzrPIUBDgMhG1kCTrjOZw3QSpGIc8t1RiuA5RwRgLOuc+YC3LQQWQP21TXmlroK9QGuSDok\n" +
                        "acFi7Fbd41cGWLSYhTRmXGf/DbBaUqJPF3gMDLkWqZM28ADY5UIkqBC8BTzZ5Nk3YLJMoEsVZH6U\n" +
                        "kHgV+34+4713wBhw0DZMvBuvAu+BkQIyu4ER4AbREnwROLr+bCIl4EzsW5ve0wPukdeDcoqEQAd4\n" +
                        "Bhy3lVuvuweY26Rm21IiyWh8DJMUKRJQUiipJymQtCxpSVJXUkfSd0nDkvZK2lGwrg0fjTEH+hdV\n" +
                        "RVxzxBgzLTXQ2QfMfP/HvyzyyBjT6V8kRcIBhynDF0njxpgL8ZtbEy+tyf0s9RQtFF1JU4oWkkDS\n" +
                        "iqRZSc+NMV+THyVFAkk7m825gUVFu+AXiv4uc5UrAm9LrOdFWQGmgfHKgTNErjYosABcbyx8ikza\n" +
                        "oUEVZoHLAxOIibyuIXwITFFgn9aEyJmKEi+BYWcCcYDPJSUeus7+B0Tb8KLccZ07FfIP2eL8dJ03\n" +
                        "q4uPSrIN6FwkE+B2gVm55jpvJth3+wA45ipn7gbRGHNS0n2LWtslnaqcqGmAyZwZ+QCcdp3TCqIj\n" +
                        "mSSfgDHX2QoTk3kKnAe2uc5UGmC/6wwej8fj+Sv4BUjRcEDGSSFRAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffecc34a.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "QQ邮箱",
                packageName = "com.tencent.androidqqmail",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAO0SURBVGiB3ZpNiBRHHMVfGY/iQXaVFRUxeolLQEKi4kKE+BkHQcVDUCEEXYgeJES9\n" +
                        "eNgJXkJiUAgJrO4tJAfBwC4KK/iN4vp1iiAEiYckCh48eBACwi+H6Rn+3dvbVf0xs7M+GIauevWv\n" +
                        "97qqu7r/XU4VAlgg6UNJC6NfX/STpOeSnpn/h86551X2XwrACuAocJP8mACGgA+m08BmYKyA+CxT\n" +
                        "+zppYB3wW4UGkrgAbGingTnAmTYaSOIsMKdqE8uBqx000cRVYHmIRhdgYp2kW6XPRjkMOOduZxFm\n" +
                        "ZVUCdU2/CUm6FWnJD2DPNEwlH/ZMpTd1agGrJU0UOgPtxxrn3N1k4aSpBcyTNNIRScUwEmmMIe0a\n" +
                        "+VZSf/v1FEa/GhpjiE0tuuMOFYrYnSw5Ioc6LKYMYlpbIwJsljTecTnlsMU5d0mSZpvCIqNxvRo9\n" +
                        "kqRlkpbkbHNI0qXWEbA04B7+GDgJPDVl56pwALwHjJq4T4F64Nqy1AY64CG/orG2NJ+7Lpq6K8Da\n" +
                        "EiY2An+aeL8D70Z1fQFGDthg4x7y1ykCTpn618BXBUzsB/4zcSY9hgDHPNrGm8SeANerpxDypU9I\n" +
                        "hokTpt0TYEcG14ceATUfyyPoE+APQ/8VWJTBnwv8YvitqVTCSE3AYBkjUUeLgfOmyWNgewrvfeLv\n" +
                        "9id9sQONDIrGy38pI6bDc4mmQ6ZuJ/CPqXuQI64PQ7PVSNtUhWWJ4zqwXlKvpJWJur8q7HehCMiE\n" +
                        "hEQCjgScuSS810cU24exzDfEUAA/Sfo+OhyVNCBphaTThvaNGqMyEHEkaYekJzQSe6VFDBcdEaAX\n" +
                        "eGCod1I4nwGfp5T/mOhmU0Y/PgyLgEeBKYLXgH8N7YfQk2diHE90tbugkXqh2y9wMEHZn9eEifUF\n" +
                        "8CYmKr+RwdwLIvCdqXoB7CpqwsT8FPjbxB3OaaQmYH4AsTf62XXiIfBRWRNG7Crgvol/M4eR+U3i\n" +
                        "NQ+xDtwzx6NAT1UmjOA+4svBGP4F+5oNcDjAdROnM7RUZejnHHoO24b9gY3G2m3CaPLNkib6pSj5\n" +
                        "4Jx7pLCE3DvA1nYakCQaL2ohKamJSHus8b4cw9ktaH0YSua1LkjaVsVZ7QAuOudqzYPks1bbL+QK\n" +
                        "EdMaM+Kcu6zuzvs2MRJpbWFSNp5GgviGujf/+0jSx865l7bw7f2sIEkRcW/bJeXH3jQTXhCe7esE\n" +
                        "MlNNb83H0CAwAz5P5zEz8zcMJAzN7C0cKYZm9qaaFENdsc3Je9fKA6Zx49n/f00ONq9Pe30AAAAA\n" +
                        "SUVORK5CYII=").bitmap,
                iconColor = 0xff76afdd.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "米家",
                packageName = "com.xiaomi.smarthome",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMQSURBVGiB7ZpNSBVRGIbfU1YQFbZoYRYtqkWujHDTqrCyiLA2EkFYRNAiUMM2Edim\n" +
                        "VYsoiiiJImjTJimIyIWK7VKyUJOSgsroB3IRBAXxtLhXHMc7P+fMzHUW94GLoPd73+8d5pw554xS\n" +
                        "hQqZYEr9ErgQUjNljOnOohmgXtLBkK/0G2P6bQSj+AtcBjYn7r7gdwR4HsN3h62wDc+AsKsY5FEL\n" +
                        "XASmLbwyDTLDZ+AcUB2hvRt46OhRliBe7gPbPXpLgHbgbULdwCBBgx2r5MGMSHoh6WRKejuDBntV\n" +
                        "SgZB1Bc/mbOoHCblwCVIq6ShtBuR9EfSVUnxnxMeXIJ8NMY0SNoj6bGLqY8JSR2Sqo0xbSnozWIz\n" +
                        "cwB1wE2HGegJ0FzCu89l1kocxFOzCugCfobU/gOuA1tCvIOCXAHWZx7EV38CGPXUTAJtwOIYtd4g\n" +
                        "44D71J00iEenCdhn6d0H9ABN9p3PF0sliKN34G0XRu6eI8aYNy51uQsSBHCUkH1SroMANcAl4Kuk\n" +
                        "e2HfzXqt5QTQKOmspNgDPndBgF5Ju2zr8nhrvXcpymOQLy5FeQxScrMXRR6DOGEbZCyTLuayxqUo\n" +
                        "7qx1W9I1Y8yIi4kldSF/m7BS8qw8O4CVyfqyA/gess7bait2IKM+o3wPRyxYl6dtuC5VwVndnpAQ\n" +
                        "L8NqrQY70AjckXQmUceltRskzdv6ehhIw+QQ8Mh3hfYnFp7rMRBxW7UmET8GDAYID6YY4m5EiCmg\n" +
                        "xlV8I/AjwuCps8Gsz94ID4CuJB4CbsQwGcJzYG2hXUVhrxHFp6QXS8C2GEYAv4BTFrrNwHBM7c5E\n" +
                        "ITymNodvvcBxYN7TmcKJyi3gg4XeMLA0lSDFJl5ZmM/wm8LZVh/RY60Uo5S4IEHEXjJDau9M4jAm\n" +
                        "qcUYMx63IPYD0Rhj5HhSbol1CCeABw63SVy6gQ2ZBvCFacfubWwUr4GWsgXwhanH/e3sDO+A88Dq\n" +
                        "BQnhC7QJ6CR4KeNnmsI/B5wGVqTVh9NGPwhgmaRaSWuLP2tVmFC+FT+Txhin454KFRaI/2GcxYI4\n" +
                        "igSOAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff5fce6e.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "和平精英",
                packageName = "com.tencent.tmgp.pubgmhd",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAGcAAAAyCAYAAABBAsW9AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAiISURBVHic7ZtNiGRXFcd/V4KMMEOXmzBGcUqZBCRIV2LAQKJT4wcoWWRQBHFhatAB\n" +
                        "xcX0gIhuTM8qLoT0iN8G7FmZRdAeUIygpFoMQgJONaIMGEl3EHTlVOEsRhCOi3tuvfNO3fdeveqe\n" +
                        "iOH9oZlX9+uce8+95+vegQ4dOnTo0KFDhw4dOnTo0KFDhw4d/n8gImdEZPC/5gMgtGksIqeArwLH\n" +
                        "tOgycKqh2yyEMDFjfB14V6bdbeAfbfixCCFc1vEHwNNAD/gR8GfgBW22C2xoPcAV4KYZZhvo6/cW\n" +
                        "sGPqDkII+56uiIyAJwwfZ5t4FZGh4QngrSGEqW93V02HZXB1iT67wND8/hBwpiWdRojIbghhTBRK\n" +
                        "onc3UTgWtv4E8L6KITf0L+FK+i0ifeCijnUaeNTw8SRx856sYdfX/VJEbgC3QwhfTIV3sTpmIYSx\n" +
                        "iLTtd/wQNOswdf8ugzbzP2a++5QFZ7HZYsyEh/UP4FDCOQD2iSpgFXybQnVUYURZXV5VmpVIqjOE\n" +
                        "MDEb5iLwF9f0gvn+IeWTZbXAi8BzwC3glSb6dwJz4ahKKNkgEdkEntSfuyEEq57qMAMm5NXXGPgw\n" +
                        "8QTdAn7jdbmqWCucbeXPtvkA8GDizdHYA9aJauejru5+83038KmKObwdeNz8/gXwTf2eAMm2lGyO\n" +
                        "KT9NoSWO6++p/vVdn6vEDTAxZSWb09MJWdgFWhOR0mKHEPyiQBTMEDhHXjhD4Bnze67Ll4Xq/N/W\n" +
                        "jDEiqpce8BLlhZgQF2gLGFC2hxZ9yif8IH2o8R4rL2BOldlEY7U/CeMQwrbh3/bJqkKr1gbUG/dB\n" +
                        "YsjAe3szYKiqZVQxjlcPq7itvs/czujE1yg8Mtt2Dfgx0SDfZHE+djEPKKtu3xaYCyNbR9n+7Kbx\n" +
                        "VFM02qbDOAQec8Ho7+yiZ5wIf1qXgR/bLs6I8iL7frbteaDKo3nV1ZXs5Are7Rlp8J5CCKXNboUz\n" +
                        "ZVF39ylUW7IjVRjZeIb6+OfA1PdEpJfz82vghbOqsR5R7dYPKau8+c5/vWAdgqSKkrH+g36nXTgh\n" +
                        "HsU0mWtmnPMhBBuwQb1Htk9ZeDmVWYfSaXMOxbaOdZI4j0cpbM6MYj5/BT7SgmbdxoTohEwpHB0o\n" +
                        "Cz5t7p4pezPwnqoBvVqzKmGXxQUbmvqgxK4kQ2fhj6jD2DE+zNDKQh0XK/g9R3df7c5PMt3XiI4A\n" +
                        "wJf1+4lMuxxyzo/FRsajtGps4r1dEbHZigV44RzLtqrH4yKyDoupC13IzRCC98a8GmqKeyy8d5VT\n" +
                        "h3XRecIJpbts0HiZ8gZqw/NK8MJZZlIe3uUE5oJ5gXicPbxwzpi0x69yRERki6jOPK3cibttvmfE\n" +
                        "E3PUOArhvFJX6YVzmCh4rl6MYAZk1EHGY7M7+N8V4w/IG+8czw+bbxsMW0fkgzjBJlWs/H+MvGpM\n" +
                        "9Q+54vUGbyzFiQMKu+M9wK+FEJ5Kv9uenDqjuK8ErGDqcI1yBN6EnMqd0XxyLKwj8iZf2eTqGgyA\n" +
                        "x1zZVq6h69NkV78CVArHMpdLUP6nZuBJC8FAdD4GLKrFMfmo/Tngef2eEjfKfi6N34AXgUc42hjv\n" +
                        "qGA9uQUGrVv3u0zn0zUDr7GEYETkYqb4ZQr3M41lsa5tPE7pHRPAXkWs5MvSBnsk09Y6NPcBP8i0\n" +
                        "gXJuDYp1STakT8xEeLxGvGO6lakDd59Vt3tyqsEaMG8Ql82PNR3/VfucJa822gS3S0X8NremmH/r\n" +
                        "5qvyAN8JfI7owV5touP1rt2xOftjVd0ddyVbwvJmT+9N39DgvqMiLiJDEblO3Ei9mqZ9YFtEXhWR\n" +
                        "2hjLZqU3KE8qp8vtAqx6194UzKF82I2Sou86/A2y2fV/uXbWe3u3q7tCXNgB8BYqhKc0PklUTyeJ\n" +
                        "Gficndwl2tYeUbNYYSQhbRKvw6dQXLd7gp+WMgYisml+j7XMYijNaK3GlFaJTou+GzU8jt2c7Ldo\n" +
                        "/4GIXM/M45yh0TTvfdve9Otn5laCbW9tzg2ipPeJdw8TT0DLLhF3Si5e8Tt8mskO3GlYlZLT6zsU\n" +
                        "Kajcye9lyg9YPvd3GdjKOSfqWQ51s9k8ZRa1r28k5qiSbZm6rPMdg0R1aRd5smzWWic+1D47ElVQ\n" +
                        "WuypbrA0/hR4B9Hbmuhm6xGTpafRuC6TM8tmRVjNtbfjDl6vNe7Q4Y2LVo8Kc5Dyu4J5IGjK54/x\n" +
                        "xF2qiUhfU/zZMaropLcLqp68V9ejfFc086pC1ZJtc1DHh9KZv/Bx/WeqKkteYsX7ilZYyC+tgDHw\n" +
                        "ceBe1E7oZMZEg7ypZZ8HruskUpZ524xxXsd4bw2dzwL3ikh6xLelZfdr/YDouo6BB5R+zlscEXN7\n" +
                        "D2qb9N4h9Xsg8aFzuQ7sSOF5JhongIeMXZv3VwEeCkeVX3reGc3E2DaF/39cy7d0khcpxzw7mdtU\n" +
                        "YB5XAPw+hPCMq/5jCOG7IvIdWxhC2FLnoCognIQQnhYRn3yduLkMiAnWHZwXF0L4ueFxTreCXmsc\n" +
                        "lXBOisg2hes8IHpC6yy6i0Mt88f+nO7SGyGEZ11dWpQvicg9wLeavDdVUXXZgXWJd0jruAciKtRt\n" +
                        "Vcd9HWedJR6jSAwqAX4dQsjlJ5fGkQgnhPCsiBwHvqBF6R7nGnHR00k6IKqEEdFNtXHUwsPBDC5l\n" +
                        "2lS5npeIVxJVC7qn0biPyD0fQ6ItGxNjlHQS98ig6g3aKjgKm5N26VkKYZ+ieEQ3o9j5+zrxXGC6\n" +
                        "LvG/X7TR1T1iGsmfzhlxA8yAT7QYr4qPHaOu0lxE25VopzIjxJVxqJOjDFwgGsGXge9r1Tcorps/\n" +
                        "A/yJaFT/DjGrKyI/S78p0u+BvI2YABcyp+Yp4G1K/4K2+yfwktIYAO/PjPdT4ktQD8tHwvfMXB7T\n" +
                        "8dMLH3tzmq4RUt8U5Hbo0KFDhw4dOnR4Q+K//kg0aB35IEMAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "起点读书",
                packageName = "com.qidian.QDReader",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPJSURBVGiB3ZpNiBxFFMd//zCKwYAYYWHXo4mnySmQDyIkS1ZISIRFQQ9JriIi4sGr\n" +
                        "7OQiiBc9bw7ePIgYwoYQlewsRNwc4kEXRA8iKLOg6EmDxMPfQ/eMPT29PV013TPZ/KDpj1cf71VX\n" +
                        "va5+VaJGbM8Dh4GF9JhPD4BtoJc535W0XVfdmrQA24eBF4AzwNHA7HeAG8CapLuT6hKF7Uu2N10f\n" +
                        "m7YvTdOAJdtrNRqQZ832UpMG7LO92qABeVZt76vbiAO2b03RiD63bB+oouPYwW77BHB74taYjOck\n" +
                        "fVWWYE+Z0HaH2RsBcDvVJRzbF2bQlcZxYSd9C7uW7aPAZlQLNM8xSXfyD0e6lu39wJWpqBTHlVTH\n" +
                        "IYrGyHtAu3l9ommT6DjEUNdy8iH6IqLwbu7+VEQZoTwv6cv+Tf6NvBVbqqRFSYvRaoUzpOvAECfz\n" +
                        "nHMNVXpZFQBCGuKcM3Oz7Bt5vTa1p8dAZwHYbgPfBRRwWVKlD5TtdWBjTLKupK7tU8B6gB4AhyRt\n" +
                        "tdKb04GZQ1kZI887ixBOAwNDlgMzn0xbekB/oOefT4Fl4MOW7TnC3WU+/Szc76Au23Mt4MiUKpyk\n" +
                        "+4zjSIskSBBCl/LBu1EgX2n4G7PQ4v8oR2XKPFaRzPa4wT4p8zFvhNRNjiCpWybPyOoeQwvYvlbT\n" +
                        "v8LAW9lezwuzhtRUX5ZrrWIDK7EIPAU8Q8FsNIIuyd/oMhGz7z0kkb8Y7kn6FNiKzJ/nD0nvSDoE\n" +
                        "fB+Yd7tFvCF/p+cnCmR5z1VlsPcy15UiJxm2W7kCQugb8mRekPVcHg0a7NRw36TpDwKPBOrSm8SQ\n" +
                        "v9LziCEu8Fq2n5X0o6QfbL8JvJgRfyzpo/T6RIQuPWzPRXqKvamC8+l9qdey/WoVjWxfj9Blrqzi\n" +
                        "Mn7LVd6pYIhd8mF00iCdCCPWAfru9yphH6lPsjeSOq4WQOvYfg34Gfgn83w/cBDYG6BDn6uDK9vt\n" +
                        "wFZ4Jc23ZPvdfMklb6QJ2pCJotj+GjhWoQV6kp5O87wPvA28LGnwlmw/TuKW+8djga38EvBGhXSb\n" +
                        "ko4PPXGycFOFlUyenzLPb9o+bvvRQKVHsP1LRV0GwYd8XGuNapGUD4DPKJ7O3we+BX4Ffk+Pe8C/\n" +
                        "6XF/h3NWXuUv87qk84USJ32+Kp8HpG2C8lUtT3dVKpbVvN4j0XgnAeINHtz47xZwUtKf2YcP77IC\n" +
                        "QJrwYuMqhXOxyIixOG7K0BSlM4eHZjG0Et4Fy9Mhxuz+DQM5g3b3Fo4Cgx6oTTV1bXM6D5xlhtuc\n" +
                        "JjYki2e48ew/UdO01X0TXBUAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "高德地图",
                packageName = "com.autonavi.minimap",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATUSURBVGiBzZpPbBVVFMZ/56VGCQERErVaTTRIorIQ8Q9KxDayURRNUBNDorKpmhiV\n" +
                        "YGJ0IUUTdFETQ1hYQ6IJ0gioUayBhaFNRE2sVVRaLYp/EGitrbUQJaLhczHz2ul0/tyZN+/Bt3lv\n" +
                        "7j3nO+fLvWfmztwLCZA0T9IaSZ2S+iUdVQJiOFqSfBIwLOlrSW9JelDSnKRc4wQslNSWNXLBQsI4\n" +
                        "LGmtpHpXEffkjVRlIWXsk3RFOE4pFHQtsM1J8anDlUCvpMZg47gQSU8DLbXOqgJ0BsWUwKsJYP0p\n" +
                        "S8kdw8ChwHVr+U95RJprmk4+7Ad+BxoCbQslNQOUJF3C6S3kR+B5oA24PKJ/FXgjcksNk8qCg8CL\n" +
                        "wJ3AHGBGjN0iSfPrgJW1yswRR4B2YIuZ7ZW008xuldSR4HN3HXBBbfJLxRATAj4HkLQfuNbvvzjB\n" +
                        "t74OcHtSVg9/4AloN7NPy42SvgLuM7MxSTNxEBI396qNo0wI+CjYIekLYL2Z9fhNNwBnJ3DNqKtO\n" +
                        "jok4DmzBm0Jd4U5/JLab2dZA86JU1iIXQSHe8BrrhKTXwkuLkM8+Sa9HtO9KCd1ZuJAIASclbU4S\n" +
                        "4Pv1Sfo4pu+7NCGFTq2ymADeBNqiplDIrx+YCSyP6JtGcqEDUK0aeRvYmCYAQNL3wFzgNjP7IcJk\n" +
                        "CTAtjadoIe8BL7sIAJB0ALgUeMzMdsaYpRe6T1YEOtJqICLuT77vhhS7tEKXCij2XVkF+Mn94vt/\n" +
                        "4GB7oJpCPswjwE/skM/RJ+ncFNszJP1bDSGT3spyiDji8/wt6UYH+6WuebkWexewzrWIY5IaBM7z\n" +
                        "Lx8xs08c3BZnCVC1EQjEGApwPpfBz6XQpYSpVYgAP5nhAO/mjL4H8wopTICfyGiAe4+kMzP6u2JK\n" +
                        "jTQCY5L+NLO9FYoYw1t2ABwGHjazfzL435w1YBw2SWpIZ4jkPBbiuisHx7NZRsRl+F6QdFaGBP4K\n" +
                        "+a/OKsLncS10Seo0SZ0RPOE6OQk8ZWatEbbB4MeBoOgNZvZ4TiEDwPl4t/58kLRSUneE8iFJq2J8\n" +
                        "ToRsd+ROgElT/v5KeJBUkvSkJpYUQXwraXnA9r9Q/zeSLqog9uIA14KKhARIL5T0krw3vTD2RLQf\n" +
                        "k7SkwpjPBPiKfdWQt/nTHiEmjAcKiFUu9AFXn7hid0HUg7PFzNbl5BuHpBFgNhkKvS4mobwYLIhn\n" +
                        "tv/rmltXKd0mE8aFSFom6aqsBJKuyxO4BIzkcYzBoJ/MXGArsE3SaknTM3BkW5p4GCnhff0uCuXi\n" +
                        "fAOYDlwGLAXaJS1z5MgjZKAE9OZwjMOgpE3A9f51D9CN972qQ1KrpHkpHDfliNuLpBUOt1RXPBH4\n" +
                        "/5ukWZr65fFXSc2SLCqjnHEbkHSOvM34orHATyxun323pKaQiKtzxNkBUDKzUeDVHMOZhBVm9mWK\n" +
                        "TROwW9IrmnhdyFMfE7lLqpfb9yMXrAlGkdvJh1F5Z17ezRjrnSmyJDVmzTgCGyN4sxzhGE03GUdw\n" +
                        "v33i5IP/qSfXwyjA8Wgl/sAsR7teM5v09jrpyW5m3XhnPXo4fbHdzOaHG6csUcysz8yuAR4CPqtF\n" +
                        "Zo54H7jDzO6N6oy8lwch75zK7Xjb2PUkbJ6aWVO4TVILOZcdeCuFfqDDzH5OMv4fXXPzF2fedwkA\n" +
                        "AAAASUVORK5CYII=").bitmap,
                iconColor = 0xff5292f9.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "百度地图",
                packageName = "com.baidu.BaiduMap",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATgSURBVGiBzZpdiBZVGMf/j2VkVCuBurBlFIohIkax0BdrUEEGXhS7dBkkgRfVSqAU\n" +
                        "kVtZoYRtXzd7EwhBJgZCRiVkYl0IrqGWmYUtrpurae9+5Ee57a+LmdedHc+ZOTPvCPu/mfd95nnm\n" +
                        "nN85zzvznDOvySPgcUn3S7pdUpPPL0DdZrYtxBHY6TCPSPpa0lYzGwxuFdgJnKM6/QMsK9i+TyPA\n" +
                        "xpCL7KkQIKmLwAMVwQAcB2b6ggevEERd48CdFcLgCvrkCnV+DKgRpdd4bJtXIczBuq8lgvol3Rza\n" +
                        "iEdjknZI+lLSMUktktok7ZO0S1JN0gZJK8zsVCiMpKUZLmvMbEM6qL+Bkd8GvAH05fj2Az3AwtDR\n" +
                        "yZmZQ76gojD9wFsUv9NdBD6oAOZsVlAozG5gU0GAtPZXANPcCMyPwOcNQtR1pEGYzmm+ADO7RdJx\n" +
                        "z+ljkr6R9FhoB3I0H9gT4mhmD0r6Nm2fBqzNCPLBbJf0bGgvA9UKvBbi6ISJp8YLE/sk0+wocLii\n" +
                        "lErrJPB8KHkizS6lVlfAzAzEX3+VtCCnjSEzM0lDoZ2KNVtSM/BciPOkmUmNSN7MHAeGA0a2FvvX\n" +
                        "Ss7KgVCYuJ3ONEgIzNgVBvkPeCn+/EIojAvECwPM9vgPA+uAlpR/DaLiDuhMBuS0vzLx+cUQjqs9\n" +
                        "9i5AZvZqyt7q8B0xs6a4U4uBdkkys+6QDni0JPH5TWC6mWXf0XKmeW3K9ymHz9b43MdMVLg7Y1vZ\n" +
                        "GfnCYXs9i8P7QIyVvpvtcPjsjo/LlKimG9R2h+1lYL0vIA9EimC6JMnMBhSV6knNjY++KqCMbvTY\n" +
                        "V+Nb6uakVlJdsf/JlL2eRuuAs67UAu4FFgWmVg3Yl9OXdxsBAegCel12zyDVQXYnbJ1AZ0b7m4Hz\n" +
                        "AX2ZdDMxcKx9s/W+3HXWoKR+Scn1wRJJMyUhqU/Se464d1Lft0hqD+xLt5mtklR4RohH+asScSE6\n" +
                        "ykR6hmqjFPZjT2umpFFF6++qNSDpuoIx7cC0MiCS9ISknpKxPvUp2tksoj8kzTez8bIgkrRGUm8D\n" +
                        "8Un1SJpRMGZQ0h1mdkEql1pJLdZEeV9WA5KaJc0pEHNK0iIzG60bGgWZrmjNkX5Ihmpc0meSlheI\n" +
                        "OS1piZmdmWSt6G6zq2RcD1HZHqozwFwXXZnniE+9ku4q4H9Q0q3ylyNp1SS1mtlvrpONplZS6yX9\n" +
                        "Feg7LOlahUMMS7rPBwEsrQqkw8y2SFqhKO+zhKRuTRSbeRqV1GZmPzsvVi+PSuZ2Uu2pC2/O8f8u\n" +
                        "9lsOXMjxHQXu9o5IVPsBLG0U5LKaCLghJ+aRhG8WzN/APQEQDYO4IK4C/s2I+cUR44I5B7QFQjQE\n" +
                        "4oK4hvwd+ZWejiVhzgMPFYAoDeKCmEH+ftcJ4O2MDtZhHi0IUQrEBXE9cDog9vv4+EoWTAmIwiAu\n" +
                        "iCbCXqIOMRm209XZkhCXQA6VhLiJ8BdCrncaT1cEATBHwEclIGYBvwdCfEr0UsilJyuAOFx3XlUQ\n" +
                        "ohk4EghRB3HaK4AA2FQPeLgARAvwUwEIn6qCAHgmGbg6AGIusH+KQex1XeDDDIjbgL1TDALAvWzw\n" +
                        "QMwDfpiCEKF7XxKwgOjt0VSCcO5qenfPif5i0SFpoaRZwfSX608z68iCUPR/FZ9GFa0+eyX1mtkJ\n" +
                        "l9P/pNUyGMqYkZIAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xffbacd80.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "招商银行",
                packageName = "cmb.pb",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAUMSURBVGiBzZptqFRVFIbfJbfsj1e0IjO93UrjVtonfQgiFqlkGZSKVCoi6J+oBAkK\n" +
                        "iSxCApOi7o800ApRMz/Cbl8SZGUkFUHdEPwRF7u3DxXLFEEtePoxZ2TPnn322Wec0V4Y5szs9a71\n" +
                        "vnPO2uecfcbUJAAdkm6TdGv23i5paPbeLumkpH5JA9mrX9K7Zra3WRoaBnAtsALopXHsAZ4Cus6F\n" +
                        "gSnAm8C/Z2Cgii+c7e6zYgjoBNYFxLwHbGnAxD5grvfdUSp7+bJWmVgA9OUImg5MbcDI0ix3nsnJ\n" +
                        "zTRwPuG9UEWfE1u2V4ZkvNWRmIeaYeJCoKdAzNNO/GMlTGxweLcUxK47ExOXA18mCBrucAYDpxKN\n" +
                        "TPbq7S2I/6wRE53AjwliNga43Qm8fQHekgTeO2WNxHrCxZ0B7vUJvKUB3qDEmqtSTSxITJh7RgZ2\n" +
                        "FnCH5PC2JtZeUGSik/wp1scTkTyzIrwNEd5dibX7gM6YkTWJiQAuKPhR9ufwoucG4FBi/fBMBkws\n" +
                        "YWJtTEyWb1mAV9fkAd5zJXTUH2LAphIJbk8QdHGAV9fkAd6wEjo+9snTS5C/KxLj5H3b4wabPMDb\n" +
                        "VULPdZI0KOPenypO0poGYzea2bFE3islasw4vQV8k+j+HzcDcHVRFSr3G1DyArDEHvm6ShhegtTt\n" +
                        "FXsJGFogaCFekwMzgRl5nEbQJummEvGrvc+LJR2QtDKPYGZrgWEB3ilJ7+fxgPGSXk3UtapN0sTE\n" +
                        "4N1m1usUmiNpSCYq10iG070CjJM0NdseZWYDIYKZ9QKfJ2obOUjS6MTg0N6QpDHA1BjRa/LFOdsh\n" +
                        "fJuobaRRmYunFQQeNbPTvZA1uXvcbzWzWUXVgPMkHZM0OPvqNzOL3tKSevkO/JTQ5Cs9zouBmEsS\n" +
                        "aj0a4D2QJDQh+d8JRq7yOH8GYp5JqPVDgPdhAWdogj4MOKLKQloedprZNCfxHEmbAnEDZpbbb8AU\n" +
                        "STtzhjvNbH+EW3gOapP0i6TxkRi/yRfmxI0CZphZ3pQaa+zFkpZFxg+rYCo2oEfSvTnjB8xsRPUD\n" +
                        "cI2k2BJnj5nVneioLKfm/uKS+s2sIyYUWB4br+6RPLzufZ4fSybpPqDDzPycRdPsaGCWmW2JxOyK\n" +
                        "ZiB+0z/Ciz2Q0HcvBGocTODtKDAbu/7aHltT2u4leThBDMBBjzcvkQfe7FgawF+BpNO8mI9KCJrt\n" +
                        "8FLWxqp4vkDngzm8LssCdsi9rpf6zOxKJ8E4Sb1Kx6dmNoXKneSeEryaujlmQlPxnrZs43vVGinb\n" +
                        "5D7uBsaquMl9XAHMNLOtkRjfCGZWmQiAMcBxZ1e110TCHyUOjypeboADsC3mFLjIix8lSeYErJG0\n" +
                        "SNIRMxvmfN+8pf109JvZz3mDwGFJwyWtN7N5/uAkx+X2YIb/AYD1js66Jdtq0DYnaMlZ1lgI4JGk\n" +
                        "QxDoAgac4BvPos4oqKwtVJ9ZDlD0rBGY7xg594+OMwCfOLrSZlFqn3G80WKNKXqedPR0FzNqyZsd\n" +
                        "cnwZv4UAbnZ0vJUXZ3kDWZLNkmZLOiHpniZrTMUKSRMkvWZmjzecxdsz5wormvKTkPZcsBU4BCxq\n" +
                        "ignHzHxqp+ZW4wPghqaacMx0UXvSbAV+BZ5tiYGAoUlUHtMdj2sqbWA5cGkjmqKzVoKhMZLmSrpD\n" +
                        "lZmlPc6owwlJuyV9JWm1mf3eqJYzMuIDmKCKqbGSOpyXqf5PZ7tVWRg/2Yza/wEn6/3reh9oQwAA\n" +
                        "AABJRU5ErkJggg==").bitmap,
                iconColor = 0xffab1b31.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "人民日报",
                packageName = "com.peopledailychina.activity",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASqSURBVGiBvVrLceNGEH2jcgBQBMbNvpmOYOGLr0IGgiNYZLDYi0+uMvfkI7kRkIqA\n" +
                        "dASEIiAVAaEIng/dQwwhkpgGAb8qlggK0+g307/pgcNIIJkAeAKQAsj05wTATL/XABr91Pr51znX\n" +
                        "jPF8d89gkilE+QxAPlDMGsAWwItz7nCPPmaQTEkuOD4WOjmTE0hIfiF5nICEx1GfkUxFIu8hsNTP\n" +
                        "mISGmutVEp8jHlzpvemIZEjyc4yODz0EEpILAHMD77FNYk7xnWFylcTOMHOpjpuPvCIeOw4hQ1tU\n" +
                        "Wgfj9tPwICnWYSJRGh+Q6bh8AuW7KGNJWJXZBmM3Eyh+CbejGcUvrDmi0LHZ+PpexZG3/IVkZRR4\n" +
                        "CMZaAsNVeSQLkj+xPydV10ikHL4ahV3nD9hSVvUflTnruf/IoJwJ80gFWw54d84t9fsXw7hL+A5g\n" +
                        "A+B3AD/rb31ZPYHo3ILDsrHP5GOsRsogaKjcWDNPgXZFrDXNOyTjJpDVeDOOD/Gm5XtJ8i9es/3r\n" +
                        "yIGWSHbjxkuY64aohGyk5gBejTI8DgDgnKsB/AmARp3kPkrItaDRMaE57vW3g1EWqSZFScLPXjva\n" +
                        "omAyxMZzfVA3+WUclkuOOs5HzB3JZ7NOtOWOKpi9Lpb6P2suGgMVKPE7BjE5w1fAGYVQRnsVPQTr\n" +
                        "PiJbVcgr2LfN3YReSCEyNQmS3DqSO7QtG48G0tmo9Xqm0SEmYR50XIL4yPMHJLn9GHl/FzX+h9nq\n" +
                        "wxy4fwvgSLKP7oR4dc7NKIl1g4+WEY0HDE9kY8BXFAXuIAHg9QHiD0PwDuAbgF8BPEIzdCTeAHwN\n" +
                        "Ooupyvuqsh6NejUguR5gkmtKZp9RwmtsIlyTPJt5SqvprCSnPRetH9BGpjjmsgIFWrPIAawix/8C\n" +
                        "4FnJJyRXkDotAbChOHwCIKqXFaD+ATaTSADsIKYxc841lEQZu49JAfym4xOIaX6HTMQjJOSXBnke\n" +
                        "tbVobPRvRfIT+9uol7BlsN9mWylUHNYz4Eke4/0ko1S4JbWXZXzghjIBnyi+ERaeCc99Y8m4Zt+p\n" +
                        "pxbbx2r03opkHXwn25WKhZ+QM2XYNvca6ixHyCqBdmPVsrqOcCu6oEQf71+FwZ5fnHNb51wK8REA\n" +
                        "WFN8zYfhTP0vphdwrjsj2y+UkJuT3Ov1nLZ9SBrI2ZJSWVCKy4biM38zrvW6RBeUvHDLRDyRhOSq\n" +
                        "85uFSBnIKai5RZ9tqZRP5neJzK1EtKLs3LoztWFLLAZFMHF7in8uKaQsvna9SUGZJavjWtBQCITN\n" +
                        "wJQygX7LW0fKOVuNs4Me7YxYHNeCdwCl1lc5JOn5VlCq1/5ou6+QLaKOtWk/VujDgVqXqXwfWLyP\n" +
                        "nRoPen0rSccdKwRkxjrYXKpiM7atH7+9zvhx0lYkn3g5an2MUhFEEsbZ6zX4OsxHtSPbFfBE9owv\n" +
                        "SWreeY44ZGVqtvki7Lr4llFs5+Y0bjCJDiGrzyyoJ8LGcZdg84kIMjmnDc1dNBz7hYGAjK9Qp841\n" +
                        "Fad6haNDKOW4r2t4LDnwpZoxXnPKIUnsaaCYF0hlvb7nNae7iIRQU8ggbZ0ZJFMnkH06INm6++LZ\n" +
                        "dqwXz/4DLkDyW02CnOwAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "即刻",
                packageName = "com.ruguoapp.jike",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAKDSURBVGiBzZnLi09hHMafR2yURNmQzDSy8QcoSYMxS9koO6UYys6KYpqVmmwspFDD\n" +
                        "1tIluQx2biVFbqVRCmUzktRsPhbMxPzOmd95b87vszznfc/zPOf7fs/V+g8AeyWtkfTW9vX/oZkd\n" +
                        "4A3/MtG2p2CAKao5mFtrUe4DzvLnzPfV7N5SSjcrwL6aSsxyI7dm9ooAA5IudRn2KrduiaX1rsGY\n" +
                        "3g4CTDY8ZvYg2QBOdOmLOdr2Wgsw3DQE8LGEh+SlBfRLuhUwpciyytEjdwLHv86g2UFSEOCipIHA\n" +
                        "aU9TNLMDHA7oi79Z37b3OYChyBC9c8UC1gEzkTm+lPIV0yMXJC2J1HsWOa8rDhkMnJZ0NEFvLGHu\n" +
                        "J0m3bX9IOIYEjMT2RWb2VPlrVBFgu6TJpDORl/75lenaI8BaSeeKWYpjcP6GJs1+RtKG/F6SCHte\n" +
                        "A8Zb7ocqHoeGONC24xp2hYQYBH627biC8TrPlVct4JGkTUElLM9D25vrdnYEAfokTRW1FMdO23fr\n" +
                        "dlZdtZYVNBPL2EIhpOqKLJc0XcxSOPds7+g2qKMitr9JOlLEUjgzko41Gbi4aqPts8C0pCHVf/Zs\n" +
                        "QscdOJCTtp8kHiMN4HnipfZaiF6xj9iSVibMnZa0P2RCySArEuaO2v6azUkKCUvqSoxe0BtiU4Cl\n" +
                        "kn5ETP1se3WMZqmlFbusDsUK9lKQy7avZneSArA1sC/ep2r2SkV2pwr2QpBTtl+kCrYd5KXt4zkE\n" +
                        "SwVpelevfVEKpc2KjNj+nkuwrSAPbJ/PKdjK0rK9LbdgqSAL/T4YLiFYKkjd77UJ26H/HNsDWAXc\n" +
                        "n3f3Hi2pWeTpdxZ+/wLYKOmm7bBPnYH8AqH34VsrgGUmAAAAAElFTkSuQmCC").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "京东",
                packageName = "com.jingdong.app.mall",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADkAAAAyCAYAAADm33NGAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASgSURBVGiB7ZpbqFVFGMd/Y2EahbdEC7MjJZrlhcRuJPiipaGpdKILkm8hIlYa3Sws\n" +
                        "CEKkrAd7yDQikgoStEyL0qyQfKgO0Y0THLUUrykkoSX8elhjZ7Xcl7X3Xvt4DvSHYfaa+eb7/v89\n" +
                        "i9mzv5lAN4E6BRgWy+WxHgT0AfpmSp84bD+wL1XagR0hhB/SvkMX8C8JdSxwE3AbMJ1O4vXic2Ar\n" +
                        "cAQ4nKoPdZlIdRAwC7g5ljEFuD0JPA1sDCH8XM6oqSLVW4GpMc4cYETBIeaGEDZUiD8ZWNar4KCo\n" +
                        "rerr6h5gC3A4hLAEeKPoWMACdbLaEmMPUcep96mbgR1A7/OLiKSOBOYBdwJXZ7o7Yj2siFgZTI0F\n" +
                        "tZzNupKt6joTrFNnlhutzlY3WBkzo+3HVeyagW3liF9ZwniTnWT7q0+ov+QMdEg9WgznmjG3nMj5\n" +
                        "FQa1dxW7ArDyjKZSC09rudcTuKpCX3fCOyGEpWce/vMTok4CdnU5pWLxFTA1hPDHmYbs6lppFhvB\n" +
                        "QWBIwT6PAnuAvbHsA34D2tICISVSHUB5kQeAoXWSeQZ4D7gdmAtcnCkngVOxPlni+UBKSFpUL5LN\n" +
                        "RUusRwD7QwjfZwmkZ3J2HFAKQ4HlsdSDhyLBx4DTwHHgWKxPABeQ7F3L1cOBkcAk4IrIc3gmxooQ\n" +
                        "wpsVWagfVFmtWkx2M980e1msA5urfs0mW6NqGBttL1FfbC7nmrBXHZ1H5As5nA3KjJmlbm8q/Xy4\n" +
                        "K4/AS9WOKo62Vhi/SN3ddCml8WxVgZHkghzO5lXxcZm6srl6zsK7uQRGgh9VcfZqDb5GqovVrU0W\n" +
                        "uEntXYvImXFQFl+rC3M7OtvvaPVh9dOCBX6i9quX1EB1jDpRvbBecWV8X6s+ou5qUOBOtd5NSddB\n" +
                        "Ha8+pf5Yo8BvjRmAHgX1OnWF+msVgevVuhNf5ywlmYVJ0mkGyZatP7Ab+A7Yns2j/o/uDPWGc82h\n" +
                        "qVCvV/+Mpc1kFe5QT6jLGvVfSEqyEaiDgbUkZxwA4zImpxqNUXhyuQ68BlxTob9ni1RXAWXzuhE9\n" +
                        "V6S6CFicw7RnilTvAF7Oaf5Xo/G6XKQ6Acj9z4YkmdUQChWpZg97sv0DgFeAwTW47Xav64wq/auB\n" +
                        "G2v02b1mkniMVgrq88DddfhseH9dtMhRpRrVBcCjdfrsW92kMooWedZBq8mtjtU5x68Fstm3hkUW\n" +
                        "CvVL9Z74uUVdru7L+ae4NeVnTar9/nOnqAxMckO1ojXjY3aq74FzpaUiTLLs49Up6ukSotKJs1Ul\n" +
                        "xvdXj8X+Bxvl05TNQAjhSAihDXgcOC82v5Uy2RvrQ8BLJcYfB7bHx2638PwLdQkwLT4upHN13Unn\n" +
                        "fZ63Qwgd2bERn8X6ouYwbBBqP3VPfN3Wp9o1ycX+FD9Pr+BjQrRZ0zWsa4SdRw/t6sBUu+ot6im1\n" +
                        "PYefL9T3G+XTrNf13lgvDSH8DsnBEsmlvoNAb+DDHH42UvwxfONQZ8QZey7TPtHkoHdatVc1NWaU\n" +
                        "yfW1htCMmZwDbAkhPJlp/5vkZskIkssLVWcy3nxsK55ig1C3qRNLtPc1OQSar9Z796Au/APG6bvw\n" +
                        "wfS6pQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffef0000.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "拼多多",
                packageName = "com.xunmeng.pinduoduo",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADkAAAAyCAYAAADm33NGAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAh1SURBVGiB1ZpNjFxHEcd/baygBSIPKIocwLCBABEKZIIBYQmFCcgXDuCIKxIrJBCc\n" +
                        "bB9ARBw85sYFjw8IWUisw40PadcWSiAi7DrYsROczBpbIf7AXvPh2EbyzsYJThTEn0NV7/T09Hs7\n" +
                        "67UN/KXRe12vu+tVd1V1Vb2BFUBSQ9Iuv29Laqxk/PVC0hZJ2yR1JDVvJqOmpK4keXtW0oKkLTeR\n" +
                        "Z0PSpAxt5ylJO24Gs61K4LTZhDR5o3dVUkvSuYRHO+M5I2n8RjAa98kG4M9mM/K5G6VKknbkPAtC\n" +
                        "atWaJLODhQKzKiEjrluVlJjEiEJGTKlGk9YUGDUkTQJTQHDytRW8a9tfdEWqJGkr0HWex5Lfq4Xu\n" +
                        "F4E54BBwEmgCXUmt0twDQnqnLjDhpHU+0RiwWPF+B33M88BjwO+AS8506wjCjUuaATpOuj/7/a0w\n" +
                        "bD3wmr//FaABjAMzcu9fxaxkB48l98cL6nq4Qn0k6fRyqqQak0hwyK+pup6RdDLpc1LSX5J2V6l/\n" +
                        "UL0dDDkdacgmXyh0yedbUKJKMpOYquD5dNaOwqRCXiqMe7JA24pv9wSm0zmuurpEHKlSggLtbVm7\n" +
                        "galSS7ars0CVV3w78KLfzwIfLPS5s0D7UIG2U9I712C28DDDNvdnZxjxqUIfgH8WaG9k7UXgoRDC\n" +
                        "bAihhwl4oDAO4F6/PgEUHQlwdgSeB7DNu7YGcxo44VjS6fbCRPMFWsnz/iu53weMhxBmIyGEMB9C\n" +
                        "aAHbKS9cAO6reAbDzugV4OWkvRNbyEng4yT6u8ttpZPQ/i7pWWUOBobOyW7yiw6nJ3MsGyteFJ9n\n" +
                        "PJvramGu3CYlCz6el3RQfaczL/MxLfUd2uZUyPiysVNPFSgImWNWfk5K2pMItFvSRzMh90gakwXg\n" +
                        "lTwLQuaYlicQGX1ISMlWYKsPKE5aI2RP0rZEgDtif2//0PsdlvQFSRu8fVnSg7JdnasQokrInqQJ\n" +
                        "VZ8Sm4ciHswTdlyft7h+j4JjQCuE0Elo33LhPu/tX/h1LISwH/i2t98AbnNbba6QZzwZZiifEpR2\n" +
                        "8rT6Or4gU92mTN8VdyZb1XY25x0aPgd3Stoks6Pdkr7h9P3Oo5XN0dTgruY72VH9ebu0k7mQh1zI\n" +
                        "s6lQsmioIdP7VMg5VWQekr6WjL8gcyi1qJinnQnZU3/h01SsJ+kJDav7gJBdmTdN289l7XG5zcns\n" +
                        "oDaHlPRzH3tEFj39wduvJzRJ+rEKu5ntaqwONDQYgp5N5pWkxyWdkPSi/waEPKVh/Dprryh/k/QD\n" +
                        "H3c0WSjJdlaSnvHrV0ecr6HhULMqJJX3XXI8i8AHCvOGrL1IOSDIX2aTpGeArzhpI/AU8HoyzwH6\n" +
                        "QcOdkr6sip2MAvptHin1Ct2PAn8C/h0HpyubI12laZm6Tvm4jmpyRpnKROypWe0U362Ya4fMJqdl\n" +
                        "attS32cs+I790ds/S+Z7SdLmtT7PXVgSmq/Kvdiqb/NnXeyIgX6iuj2EsLfwbkeAnwLfB36C7eJH\n" +
                        "sHh0v8/1TSzY/g0Wqr0lE24cO8pa2LHSwJL5vU7rAF+kH+NOAx9LplgfJ6rDnK/aQBTh42oLWZLe\n" +
                        "IelL/vxup+339ne8/Vtvfz1fIQ3nmqWwrqXlI6ViMBCxG0vDJrGdrMMEtqtLx0kI4QqwgAXPl50c\n" +
                        "g+iYtp3w60BWIcvup+hrTQnjWACwDtvJY1UdS0IuAg/5C3Z9slEwTlLykPQj4EngYggh1mkWMbU8\n" +
                        "7O0BIWU232X5RU3RxjZiAtuYIeRCHsBWZZcPTlGV9uToyGo2jwCfAF5Knr0MfC+EEL3scWBXCOGs\n" +
                        "pAlsUZvYTs9iiXsJZ7F897Tfr8d29RyF3Hhtcr8dy/JnKKvJ48D7gE9m9KuYqnza20eADZFhCOHB\n" +
                        "2DGE8Eg29gRwUlYdnEjot2OFqkuU89oxv17CNKjngnYw59PCnJNVNmSuuKX6GPApv/4jczx1hawz\n" +
                        "fq2sw6qcORyUOaTzGqz3pI6nFACkQUKMudtyx9PGjLwukrkLO2CPZ/TXasa84tdiHVZmu6XM4c3A\n" +
                        "54D3AJuoVtkc14Bn/b5B3ykdXeOEOi/WxezqTf4C14MmiTOReeF47uV4a9YuRTQljDFcVHsYGFvj\n" +
                        "+d8DDLrgC5jhX/Znr2I5X8k+lsOiM2vLzrQmZkdNysWsU5hnBytcbxiRzzUGNetRoBlCuLAWIIQw\n" +
                        "J4sbO1i8eQWrwl0Afo+V++5juEo2ji1GCRuxhdvi/brAeSyymnJeW7AdTu32s5jX/Cvw/hqBcowB\n" +
                        "n8EWdSKEMF0xdimFqosgRqnxSJ5IazBamnWHEHFO5nwGknKZQ5uRdDGdL+G5qMHiWXRES7WlZaH6\n" +
                        "WstyQqZJbe4FcyEjYlK+t4qnli9k5ef6EophXVJrKUYQNdiHqeb99A/2UdDGvGGbcqG7DueBB0II\n" +
                        "KxMyIoSwDQvxRmG6HTvQo73BCLkndtS8gGc1mMfNC91ViM5lrq5TrZAAXvkep7qsfx7zwPNYlNPC\n" +
                        "zqsTwG1+f6Zi7CnMc3+YvoCTWFgZ06sSFrFoasI/O9w4uF2kNrlXZr+TiW38qsJmnivY5C99jvhL\n" +
                        "U6sYtcQEOdrk6M5lFYI241XDFbOqCoNk3zerHE9Eydntki1kS9f5/4C1y3cZRKL/PezMW5c8jsHC\n" +
                        "GeAef343ZncNBj/KjIptxjasJP0awLI2WYUQwjzDyWr8LnlP7IYtwruAdzP8eW0UbF+NgLAKIcF2\n" +
                        "teao2TfCFBdqnh3DjoZOTZ+RsCohIwpHzdPAexlU5Sr+c/47Q//L9qPYd5Xao+G/AiWfEiqwnOPp\n" +
                        "Xa9zueWQZRsrFXJON/touNHQ8FepOiErQ7L/eWj483wu5LxqPg38X0FWKO5lQk7rFv1f9pbBd7Xt\n" +
                        "aryqc2+l+A+lYLJN0kJ+6AAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffd10006.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "京喜",
                packageName = "com.jd.pingou",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAQLSURBVGiBzVrRdeIwEJzl8X/uIKaC81UQOkg6CB1AB8lVkHQQqCDpAFIBXAWYCiAV\n" +
                        "zH1ISoQty5Isksx7/sBI651dabW7tiATSJYArgFU+ir1ZaPW105fbyJS59IhGSRLkvck90zHnuRc\n" +
                        "G+LLCVQknwco34XnLyFEsrgQAReh4lIkZiSPX0DC4EjyNjcJlxdOmRU/dch8zEGgILluCH6g5XYq\n" +
                        "T20GEHilZXn9zKfGmDVTl5oWuG0IXHjGl1qBEE/VVAbp3NgOMtskMmx7Yhcx95bk0kHqzPo9MgrH\n" +
                        "/HUsCdeeeIgSkgHaGE08ucaOHJNnAGaOsaeehxYkqwglq4Cl4jr1571e1cp0hdhpz1yzptckb1xK\n" +
                        "avl31rL1epkqkLhw9Bqhw5UGLi/ZcyuqTWxjr5Vesx046j4PUgWELix9ivgQtEeorLjrkHFi3Ib3\n" +
                        "ESFdUY9+b3RbwK/I1Fyxc/X811CdRE8oAex75NYiMolUpALwS//8JyLegOGYfwTQFxAmH6VAgAsN\n" +
                        "vEuCank+sr0fbKz1mJseWYtAnRb2pNB6opXIWcqn1CRHPbdqyJxHyNgDgDBsWTVhKr0CqhrMASMz\n" +
                        "ZT9NxokTXWXsUAyROR0hn0W/E9UY+YkcALxCNRfMcgGUtQuoFXAL4CrjM6uYjd6HDSPOC6qDs5kJ\n" +
                        "pGKPDEJ2TDzwchIaSsSZUicSCj3LshPxJpGWgnOS88CxXdnuxYhsAhWzC7Qg7zGx/gfT1qe/HsBH\n" +
                        "udtEX01TMa3lVCdbgCqf6gzddEfDPTsMMIAESW5GUPE+BRWANR2JJFXt4jqlSwCtToyWsUZ/ptuF\n" +
                        "3aANZuGFusihagv5LHuk9iRVrdLs1qRglpo0dlsmLFM4QZ34ubKKiSmsauRNGb4SBxEpTTsouoz9\n" +
                        "QXgC4krdn4qJiNQjANA17yqD0PfAcW+Znrcy9bqYOzqSbCMFveMzfNciMiP5is8wajcflvhcwgeo\n" +
                        "Df9b/+7qbvbho/EwNndEZEdyBeAuQtARKv4DAEje49wYV/gkMkPbC8aQfyKeabCyX6SK/Q/VqVtb\n" +
                        "Dw/BXxFpNe+o0pFm93yF9l4UAPcRzwPUSijt9tLY/ldETlRZ7UuMVLqTSFdnklD7w0bKWTIL6pGx\n" +
                        "/ZLFB2crVZ/aNhb6XuEYF9rDImNrIMYlk0uStOb1tV9TEVQ+NIkU7G5GN0kUVO1N00Mu6c7hpiSv\n" +
                        "G5cr3XdhR0/p0HrRY6DX4BTtNd3iDLWpTUQqoSLXdZfoxhWCNwDT2N5xCwzbM/dUL3F82PI8Mz7S\n" +
                        "3ycmM/YFDJlb5n+37sOJuT8YsMgUvNxGtrHkpT7haBAqL0RoyW/6SqikOgOGNNhqLWMQgdCoEUQK\n" +
                        "KsrZH541i7UDzj882+T68Ow/U+/beKck6XwAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xfff26c00.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "掌上英雄联盟",
                packageName = "com.tencent.qt.qtl",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAFUAAAAyCAYAAAAtBJe4AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAjeSURBVHic7Zo9iGRZFcd/d/2IhKlBUTeQqQlU1GBqWIWNnJpEw+nZRAOhaxTEQLZr\n" +
                        "TCaza9TAbKpBWEGWrjYSRaY2MuxqxS8QuhpMBKVrV3TRYPsZuoh/g3NOv1uv3+vPcqt7mT88quq+\n" +
                        "+3Hu/53P+wquACS1JbWWLUcdJN2stj23DEHOgR6wsmwh6pBS2q+2XXpSJXWBG8A9Se1ly/OugKSx\n" +
                        "SvSXLc+7ApI6kgq/LqVfreK9yxbgJKSUppKm/r1YtjynwaX3qVcJ4fOf8x99SZuXMRC4TDf8upSQ\n" +
                        "tCppH2jnje0sGKxfNt8laSJpsmw5qpDUlbTtvA3rOgwyYg8kPVmG5kpqeRqVtx0hVVLnnZVsbu17\n" +
                        "GZkKn9/UOU9fAvuSnkpayzfi2r1Q0iWt+AOVpKmT2fa2Xf8+8ytkW3hRIOlO5XfbTXwzky/H0Qfs\n" +
                        "2jGt6VyHAyd5P9vYpi9657waJKmXrVHI/HynsokD3+DQ+wQuRKzPeS8jLSx1u4HEOvTOSmzR0D5R\n" +
                        "vXbnONApiJY0qsy7qvLB5diWEbCbtZ2qKJAR1YTCZahbs2n/cW/uoabKol3gKVAXqApg6teGt93B\n" +
                        "It4UuA10/KpG6gK4m1Jq9D2SBsB6w+09X+daw/3rJ+WwkjaxM4Qcr7vsM2Db1yiAHf9+D9tPl2ZM\n" +
                        "gQeNe6toy3FYaeh7IMse2jLzHVXuNWqsLFCG2fd9jdCEVyR9O9OYrsxdxPxHTW9+7n4mx8jHdmRu\n" +
                        "rA7DbP3T4Hj344sNZSbYhG2Zu9ittK3LnbzKoFL1ibXaKCO1nckQc7/in2GekuWEZOvUkuoy5iZ/\n" +
                        "4DJ1ZP5/TUd958jHbarZp85kbq+vmvQz1QnjAu1iqt+ELWAI9IFVb5ti5vPQx641zDEDHmAmtlpz\n" +
                        "v5WNK4CfAG/6vC0fPzpGtj3glstW3fTEZS9cvjZl0r7h8w4w029CATxMKR0nw1HITKQucEWKFU+4\n" +
                        "q9J0A091cgA7C3Y1bxUXxahmvpFMg7f92lSZulUx0UXyZM2nOoEwxfCfdQSuaL6gOC0KF3qg+oc6\n" +
                        "ya7jonITcn+dYyDby7psP3Vzb56byBMIDU0NTL1vR/OBaVOnI7bwPvu+mVZFhn7Wr1Y7ZNbSbyAi\n" +
                        "RxCXV0Njlb68am11/vRixKqM4gMdH7gOZM4914BCRnTLx9dlC1NfY+AbXfd183k72bVbuReFwNDH\n" +
                        "NWUvA3kwc3ly8jqaP/toQlR4Q51QSTYGqhqCxxx13juY4+/U3AMLCDPs/dKtmvu3sUCynbXt+Wfk\n" +
                        "v62Gsfh9OD6gxpx1eWyH5ty3irsppVMd6pzlkDqS4jDPPlD4IXIbywSqmKWUZv5AWljyPcEIm/jY\n" +
                        "DnDX77VTSl1ZhbTi37sY6QX2EAqc5JTSjmtpx+/NfP6Zy7OCRfh+SmmiMp3b8s9hSmnFtTgKlh3v\n" +
                        "u+1z5g9usaSmlMJ82sAYq372MHK7WGq0493jDHQLS1F6TkRUW9eBYUZIjOn4/C3KlGkGPPZ+YXah\n" +
                        "2cnb94BeSqkvqRvVlT+QkAksVSJry6ugqtV2MYKPq6ZqcebXKa55BbbREHyGlWqz6OcaGMd4Y7+2\n" +
                        "XdgHGHGFa2PPv0cwuke58S5WDrd8/QlHCRgBu5I6meXc8PUOsn5B0B7zLqND6WJmLAPys01Z1D1E\n" +
                        "fr/i5LveLpXZQlQuLZWHGBHFR1lgiUA29vux5qRmrWirZhz5+rFOV3beEJnLHb9uRN/z8nNmTVWZ\n" +
                        "fpzZLBy90G7XzAHwGub7oAxMRfY7Dm6aNHXD+4eW5b6vBazKyucw/5iv7X62R3mY0jspup+E87xN\n" +
                        "7blQtzCf1AULGtHBTXouavumNtw8xx4gIuiNKUkFM8/wdxEgk89zWNqmlB57n2uYmceDmAHK7m2d\n" +
                        "ELkfVxsuoKjnfpu6h0XXCVaPv+6CtNwnxuvk21iwmmDaFPni2OeJOaqIoAalT801MVFq6wi4D9yM\n" +
                        "udy3/wvz3YeHLe4Wqq4rXEC4kfNa4CHOS+oYi+DXU0orLtQTPNKH/0spDVNKbeB2SilI6qWURk7+\n" +
                        "KkZWaOwMM8PcvNuU+eyqX2I+oKx4dhLHeWvZ2vfPuccdWcm6fVZ3cOrkP+CatooRO8M0IWE5X2hB\n" +
                        "C8sRe8Agax9ghOTa2cF83Ws+5xA7KK8m6x3Kw/G2z/3U176BaXQHIzFOuXaz701RfdZwr4Wli1tn\n" +
                        "PY06M6kBJ65znK/yPn3KNwaRWuVoUfrThxjhu9hG77sPPlzLNXwTIypSsyc+fivyaV8/3kScJ/AU\n" +
                        "wDhPEy8VPE0aq/lAJA5u9rPfUZ+vuf9bk723OvB7kSbFeUCQv3Qs5L9UvplhSqmXaecEL2OBF3EN\n" +
                        "dfcxwzS3R6nBXWAk6RsppR9K+gvwHuAl4Hngoz7nA+BtoCt7jREV1dhTtB6A++2wkkC4gQnmZv4D\n" +
                        "fA/T9hZeVl+Uj4X8l8ojfTv7HgJOJd0FPoER1/dPZf3y8S3gN5JeBb7mruUt4PsYIUVKaQz805de\n" +
                        "w/zsFCt7w39GVVV1US3sfCH89Wcoc9SF/X9goX9Qy1KqAih8Y9eAH2NCT7ENPMyGhckGYc8DXwX+\n" +
                        "nJlzzPkxST8Cvuvt6ymlPp41ZGTlr0gi0wDLTnIX9F/KV0AjFkTsuQNVFVl+F2kRmMBhcq0s6OCm\n" +
                        "mn+P8VP/LeAN4PfA3yjfTQ2An2HvrJ7m63kga2Vzt7NxgSJb79DcY9yi+LiUkPQRSb+U9A8PRJtl\n" +
                        "6a6vLFu+43CZ/5/6KeCDwKv+ewv4FfB54N/LEuo0uJT/pJb0OeAHwE+B93tznKG2gG9J+mxK6dEy\n" +
                        "5LtykPSypD9I+roqR4uONyS9IOm3kn63bHnrcBk19U/Ax4G/U1+3fwg7d3gR+MI7KNfVhqRvSnpT\n" +
                        "0ocl/aKiqUNJjyT9etlyXjlI2pCdOH1S0ltO6B8lfcDTrWc4KyS9z7X0kaQvOakv+OdLy5bvOCws\n" +
                        "+f9/QNIt4OdYhXUXq7ZuppS+uFTBrjokfVnSXyV9+pnZLxCSviPpbUnPov0iIenlZcvwDEvE/wDq\n" +
                        "PPHz6ZIflQAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "央视频",
                packageName = "com.cctv.yangshipin.app.androidp",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAM+SURBVGiB7ZlNiE1hHMZ/702SRj4GY3xMU74VFrOQKFPjo8lQFhZESFnPytoKZUlE\n" +
                        "CSVEWQxhw0IKE7GhfCwm42OGZnxtxOKxOOfmmsY9zzudQ+o+dep0z9Pvf57Tve/7/98DNdVUUzWF\n" +
                        "osCSlgFNwIy0Th/QH0K4VVTN3CWpS3/Wi399f5YktVYJUVZn3nVLeQOBjYZnS95FiwjSZHhm5F20\n" +
                        "iCD1hue/COKoJ2/gvwrSmzewiCCzDM/ZAurmJ0nrjKX3UBG1R43gZtuAaSRPfkzFpaVAa3p+AjgP\n" +
                        "NALTSXb2fuBtCOHmEN4mYC4wFvjM3+gAJN0wnvipCN7xKpxiOgBJu4wQkrTD5O0xWLl3AEjqNoMs\n" +
                        "MXl3DNaDIoL0GYU/RfAGDd6gy4tZficYnkcOSFIzMNGwOh4gLshrw7M4gperYoK8NTz1kmZnmUII\n" +
                        "PcBpp6ikmY4vJsgZ02f92IFLwDfD53QKfpAQwkmSjS5LVpAQwjVgLfAsw7rI4pVPJLUAc4AGkl22\n" +
                        "F+gbZieeAKxIfdOA0emlSUB7yrgw5Ab/uGNLGgvMT48FFZc6gJb0/CjwIT3/XmYBt0MIXyth1XbY\n" +
                        "o84TSTm7jSU1htdp8I6UzXsN856I4t/y4kkaJ+mjwTtQAjYZzG1uELxlersDSr829wzr+hLgLG8x\n" +
                        "o6kzNDlzfVlOt9BURBBHeQcZX8Kbn+0eCqgzPDEzu1W7hPdVeBVRuMHwxMzsUx1TCbhs+NxdHbwg\n" +
                        "Ts2ylhqe/UiaquqT33G3oqQOY6m8ISnzKUtqlHTM4HVJqqvc2dtI+ppGkp24H+gJITwcUqABWFjx\n" +
                        "UUN6rASWkywen4DulFF5DDezTybZ1TeQdAcTSUaG1anlC3CX35fhPmAAGBjRbC9v8zwnaYrJ22zw\n" +
                        "9kXfaEbRNUZRSdpp8pyZXZJas2lxbfxW0/fY9LVkWwBvDooKYj0ZfnWpWZpn+t45ppggzabvfQQz\n" +
                        "Sx9+a9OrKPf/fkMIP3LEPXGNMUEeZlui9NLwPHVhMUGuGp7DEbw3hudSBM+XpH1VlsmLI+Cdr8I7\n" +
                        "GMOKfs+u5P15+5CP74cQrseyUl4HsIqkOZwMPAeu/Ffv42uqqaa/p58x6XSczznLtgAAAABJRU5E\n" +
                        "rkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "百度网盘",
                packageName = "com.baidu.netdisk",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAOXSURBVGiB7ZlNqFVVGIafJSH9GRGEDTKixMDIMq2BJWhhP1ISxY0gHAVZYWDQjXCi\n" +
                        "g0YNLlGjaJKQE6MMGwRWKCL9wS26qHDph8TA8mJBFwu91NNgnxPH01lrr73vyltxXtiTtdb3vt+7\n" +
                        "z1nf/vbaMMQQQ6QQShOq84F1wCpgIXBFZ+oH4EfgAPBxCOGX0tpFoC5SX1FPWo/T6m71prnO+yyo\n" +
                        "m9WjGQb68bO6Za7zB0Dd3sJAP176P5jo4o25MvFAQRNdbG+bT6uqpV4K7ANSm/UUcASYAGaAZZ3r\n" +
                        "4hr6tSGE/W3yagx1tObO7lCvGhC3WN1TE7vrnJjoJPRZIpFtGfEP1phZei5MrEskcKABz6YET+u9\n" +
                        "kg2rZ8YgTKvXNeTaG+F6u2le85oGADdExidCCJMNuQ5Hxpc15GllZElkfKIF13hk/NqmRG2MxPBH\n" +
                        "i5jfS4mXNNKm0hRrGs9LTaqLgI3AeuCynmsQrm+hHy0O6hRwEjgBvAnsaNz6qxeoY1btdhO81kDj\n" +
                        "3obc36rb1AtzBe5QP2oo0osNmTpt8bn6SB35yCwEerEpofFCIY3He3lDj8AIULLPeZ/qOTFO1TTe\n" +
                        "CKwA7iqo8VeDGQDUq4FPqN6x/0v4Arg9hPBrt/w+TdrEfuB54H5gLfAQ8HrhpH4Cdnb4FwPnA6uB\n" +
                        "MeBMJGY5MAqAerN6JvFffCKmbNVAHirwfz9kouNV71GPRWJPqctRn0kIPFd3G9Wltjt4yDLRo7Mi\n" +
                        "wbEV4y86B+vIe0RiHXEREz06r0Z4PkjV86znQUdggfrbP2mio7M6RpbqtbLbgRDCNFXVy8Vh4OEQ\n" +
                        "wpEGMQDHYhMlm8ZctDVBCOG72FxJI387bBiA1iYA1DWxuXnAu5G59Q0EbgOuqVk2KxMdrIyMf5gq\n" +
                        "v9Pqqhx29Z3SG3uAxiXqVIR/a119rk1A3dXGhLpGnVTfU59UF9ToHExorOwuitXnbiKj6pI+4g01\n" +
                        "5CkTjw5Y+6l6Z9+6K9VnazR29gakfpVe7Otc32esjf6a6luJuONW7xyTmTnd3U++JTMwB3W901eF\n" +
                        "dAYf5FnmM0HOvtpYQCd9GqneNwvyvXUmenRebqlxXH0sRwP1VqvNl4uj6uYs8rN1RtRvGujstk0Z\n" +
                        "tyqRY+qXEeI9VhUodkSUo3G5+pRVEenHaXVcfVG9JcWT/aFHvYjqEG4GmAJOhBBm2hqIaMynelNd\n" +
                        "SPWh6OvSGkMMMcQQ/w78CeKTxkSm6OrCAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff58c6ff.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "京东极速版",
                packageName = "com.jd.jdlite",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADkAAAAyCAYAAADm33NGAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASgSURBVGiB7ZpbqFVFGMd/Y2EahbdEC7MjJZrlhcRuJPiipaGpdKILkm8hIlYa3Sws\n" +
                        "CEKkrAd7yDQikgoStEyL0qyQfKgO0Y0THLUUrykkoSX8elhjZ7Xcl7X3Xvt4DvSHYfaa+eb7/v89\n" +
                        "i9mzv5lAN4E6BRgWy+WxHgT0AfpmSp84bD+wL1XagR0hhB/SvkMX8C8JdSxwE3AbMJ1O4vXic2Ar\n" +
                        "cAQ4nKoPdZlIdRAwC7g5ljEFuD0JPA1sDCH8XM6oqSLVW4GpMc4cYETBIeaGEDZUiD8ZWNar4KCo\n" +
                        "rerr6h5gC3A4hLAEeKPoWMACdbLaEmMPUcep96mbgR1A7/OLiKSOBOYBdwJXZ7o7Yj2siFgZTI0F\n" +
                        "tZzNupKt6joTrFNnlhutzlY3WBkzo+3HVeyagW3liF9ZwniTnWT7q0+ov+QMdEg9WgznmjG3nMj5\n" +
                        "FQa1dxW7ArDyjKZSC09rudcTuKpCX3fCOyGEpWce/vMTok4CdnU5pWLxFTA1hPDHmYbs6lppFhvB\n" +
                        "QWBIwT6PAnuAvbHsA34D2tICISVSHUB5kQeAoXWSeQZ4D7gdmAtcnCkngVOxPlni+UBKSFpUL5LN\n" +
                        "RUusRwD7QwjfZwmkZ3J2HFAKQ4HlsdSDhyLBx4DTwHHgWKxPABeQ7F3L1cOBkcAk4IrIc3gmxooQ\n" +
                        "wpsVWagfVFmtWkx2M980e1msA5urfs0mW6NqGBttL1FfbC7nmrBXHZ1H5As5nA3KjJmlbm8q/Xy4\n" +
                        "K4/AS9WOKo62Vhi/SN3ddCml8WxVgZHkghzO5lXxcZm6srl6zsK7uQRGgh9VcfZqDb5GqovVrU0W\n" +
                        "uEntXYvImXFQFl+rC3M7OtvvaPVh9dOCBX6i9quX1EB1jDpRvbBecWV8X6s+ou5qUOBOtd5NSddB\n" +
                        "Ha8+pf5Yo8BvjRmAHgX1OnWF+msVgevVuhNf5ywlmYVJ0mkGyZatP7Ab+A7Yns2j/o/uDPWGc82h\n" +
                        "qVCvV/+Mpc1kFe5QT6jLGvVfSEqyEaiDgbUkZxwA4zImpxqNUXhyuQ68BlxTob9ni1RXAWXzuhE9\n" +
                        "V6S6CFicw7RnilTvAF7Oaf5Xo/G6XKQ6Acj9z4YkmdUQChWpZg97sv0DgFeAwTW47Xav64wq/auB\n" +
                        "G2v02b1mkniMVgrq88DddfhseH9dtMhRpRrVBcCjdfrsW92kMooWedZBq8mtjtU5x68Fstm3hkUW\n" +
                        "CvVL9Z74uUVdru7L+ae4NeVnTar9/nOnqAxMckO1ojXjY3aq74FzpaUiTLLs49Up6ukSotKJs1Ul\n" +
                        "xvdXj8X+Bxvl05TNQAjhSAihDXgcOC82v5Uy2RvrQ8BLJcYfB7bHx2638PwLdQkwLT4upHN13Unn\n" +
                        "fZ63Qwgd2bERn8X6ouYwbBBqP3VPfN3Wp9o1ycX+FD9Pr+BjQrRZ0zWsa4SdRw/t6sBUu+ot6im1\n" +
                        "PYefL9T3G+XTrNf13lgvDSH8DsnBEsmlvoNAb+DDHH42UvwxfONQZ8QZey7TPtHkoHdatVc1NWaU\n" +
                        "yfW1htCMmZwDbAkhPJlp/5vkZskIkssLVWcy3nxsK55ig1C3qRNLtPc1OQSar9Z796Au/APG6bvw\n" +
                        "wfS6pQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffef0000.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "爱奇艺",
                packageName = "com.qiyi.video",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAQJSURBVGiB7ZpNaFxVFMd/f50gtLXUorG1rYpKRS0S/Nh0oSmiVakKhei2Ow26UKg7\n" +
                        "F+nKhVrrzp0IBrRRC22kLoREkIpVqR8pVds0iZZGSVtL/cBi9e/ivUlebu6beTOZZKY4Pzg87jn3\n" +
                        "nndO3r33vTk30KZNm0qo1gG2O4BO4CpgFXBDRq4o6OaXjPycXs8AvwHnyldJ/xSNK5qI7RXArcAG\n" +
                        "4E7gtkzwK4o6bwATwHfAEeAHYBQYlTQadpxOxPZq4EngMaBrceKsm6+BNyW9WlYIwHYX8AFwTZMC\n" +
                        "q5cTktbBTCJ7gUfm4fA8cAqYAs4WHNNBsqZWptfL6rz3c5J2yXYPsDun0/ck83IqI6eC9pSkc3UG\n" +
                        "MY3tpcwktRJYQzJDVgNXp3JfzvCbsL3Tcxmz/cB8g2s0th+1fSwS74vYPhAx3N/soPOw/Wwk3n7Z\n" +
                        "dtB3WNKmpkRZANudwDsZlYAwh4sX2e4LdIclDWQVtp8A7gF6gKXA7yRv37IMAPslHQvGhb6R1Jen\n" +
                        "rzaWZLYMR22R+TYU2N+I9MmjJzOuO2LvS23bIrZngvvujvS5JbUNhYZLIllnnXUD2wL1KDCcSsgL\n" +
                        "lfxl2AucDnSbg/aDQftLSUfyHFZMBOiO6J6XtCndEMJkbq/iDwBJZ4DBQL3ZybuE9MleHtj7K/mM\n" +
                        "JfJrlTiq2YuyL2h3kKxBgNiu+VYlZ6XIoJ/qi6tmBkm+bq/L6LbYHgAeCvrukzRVyVnsiaybX3zF\n" +
                        "kHSeyPQieSrXB/oBqlAChgJd3kJeCAaBpzPtZcxMrzKTVFkfUH2xLyiSPgQOB+qHg/YeSf9W89XU\n" +
                        "RFLC6RWyp4iTVk/koKSPijhpeiKSPgEO5pjfLeqn6YmkfJWjr7pblWmVREYiumFJ40UdtEoieVOr\n" +
                        "MC2RiKTP5uujJRJpBP+bRP6K6LL13cUsn1akVMUe2zVesr2FpGgdllb3NySqOqiYiKS3bd8LPJVR\n" +
                        "35hKyFFgVwNjq4kSsCPQzfqIk9Rr+zXgLpJfjF3AlSSV+SWZrv3ARtvfSppMiwSh70o8TlL1LxP+\n" +
                        "8MryCvBxpn2yhvvMxfbLeQWGxaZkzynQHZJ0R5HBkrbbngC2ZtRfNCy6CLaXkZSg5hjeb5W/ahFs\n" +
                        "b4zEewDb2yOGP2xvre52cbF9t+2RSLw7S8A3kTFLgPdsf05SxzqRygTJljwuqeg5SC2BdgBrSY4U\n" +
                        "wuvN5JebPp3PQc8FkhdmnlyowdfyNODOGmMAeF1Sb/YMcYTZ29/FwElJayDziSJpA9ALHAL+bFJg\n" +
                        "RThLsjPuKCcB+cfTlwLrSebleuBaZs/ZVQsc7GngODCWytFUxiX9GBtQ8z8MwHSia5md3PI6XP1N\n" +
                        "UoItyyQw1ogzyTZt2szmP5pchE46/z11AAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff4cc000.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "中国建设银行",
                packageName = "com.chinamworld.main",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAN9SURBVGiBzZpbiE1RGMf/a0guL+LF7UF4EI0UeXWLXEo8kFKiJKUYJYbClDTKAw8j\n" +
                        "5IEHHtxCruUyHkSJuWjwYB40bpPBRBSlfh6cM9Yce++z9tprn/Gv0znt/X3/7/ud75yz9tl7G1VA\n" +
                        "wHxJcySNsx79JL2W9EpSq6QmY8yFSvSTWsAWoAN3XQSm9nXfkiRgOFANTAcGFLaNARYBJ4BvZWC6\n" +
                        "gZq+aHwksAe4CXwpaeon8ABYb8WPBRr+m+lYAG8dmgJ4DCy38lc75HQDy/KE2JYCoFQ2zEqH+B/A\n" +
                        "gtAAE4DrngBxMKsc4j8DM0NBLAwAEAfj8jF7A8zICnEsMEQUzFqH+JdAtS9EY04QRdkw6xziW4EJ\n" +
                        "aSFO58vQIxtmg0P8Q2C0K0R9vr3/Ixtmo0P8HWBYOYil+fYcKxtmk0P8VWBQHMRQoDnXdpNlw2xx\n" +
                        "iD8bB3IwcGNtHjk2zNaEuGvAwCiIaV6txquu4HvXI9eG2R6xvxEYGjeNkOtFXYn3ZQ8PG2antf0R\n" +
                        "MCIOIuQ06mJqnPLwsmF2AU+BsZEQhaDNeUJYdY5khJmY5B9q8UuEsGodyAJTzvx9JSCsertTeEf/\n" +
                        "xEaYTskIsT8NhFXXf52IMczy/TjkA2HVTjpQdIcomPl8AQGOZoGw6kf9U0wHUTC64QFxMgSE1cPi\n" +
                        "TBAFk0spIc6EhLD6mASc98ntX3j+mDLviE8xB3VK6gIaXROMMbOlvyBdKQs2ACuMMc9T5pVTl6Qq\n" +
                        "n8RiUtqJTJZ0FpjkUzRKwHelh+gZQFXphhQKBgN8kjTYI/VZ8YXvRIrKDAO8kZT8lzVePR/tIkiT\n" +
                        "byPKAAO0S3I7iRCt3hMxxnRKepjBMDUM0CZpfIaakjUR27jWY1EsVZsLDH9OaGfVV9vT/pW4l/Hd\n" +
                        "kRwmA9yXNC1ArZuxe4AXAd4piJkMcDuQP1jXXKJAdgQs1AsGuBLQ+3HirIBBQEvAgm3ALOBcQE9I\n" +
                        "moYFsyZw0dC6FdW3iYG5ISnsVaIw6pY0zxjzpHRH3LFNvaRfubbkp9ooiEQRZl0JqXpvfCp3faSc\n" +
                        "sv//AQ73McS+zBAWzBLgQx9AuJ2USwlTzZ+7ESqhZmBpcIgSoBr8bxZw0UHiLhfkAFO8faM9IMAx\n" +
                        "IMTBpDfUEuA48M6j+VZgbwiAyJXdV8BcSXMljZQ0ynoeIqnDeryWdMkY0xKq9m+hHJ1npgOuZQAA\n" +
                        "AABJRU5ErkJggg==").bitmap,
                iconColor = 0xff233d8c.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "虎牙直播",
                packageName = "com.duowan.kiwi",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADcAAAAyCAYAAAD4FkP1AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAV2SURBVGiBzZp7iBVlGMafd9lSIlNaQ0sU2cp0MbAsIxU0ygt2Qa0NUwwr2IrUJDL9\n" +
                        "J9ggkIKykFC3iyUJYmVhFqbYdtss8bKaIhS5aka2EYWWZRG//vjmrNP4zfWcPfbAMHtm3svzzMx3\n" +
                        "e7+VMgJoBt4GlgKzgUuz+qbEnQKsAjqBlcDISsTNQ2AmZ+JvYD1wD1BXIOYVwMueuFRKpGUk0iZp\n" +
                        "dIJJp6S1kpaa2aGI7yhJYyT1Di6dJ6le0jhJfVNSzzWzF7JwLARgWszTjUNr6Pgrp68Pzd0pbn0F\n" +
                        "CJaLQgITP0tgtKS2Qk+l8phkZpvzONSk3L+9DDKVxgfA9XkcalPujyqDjCRtlrRX0k5JbWb2Xfgm\n" +
                        "MFyusxkhaaSka1PivQZMN7N9ZbECegB/FmgfbcBioKFAzonACtyYF4f3yhIWJBqbU9R24M6yE7vc\n" +
                        "A4AlwImYXOX1oMAjGUWdAhYBaZ94EQ4NwPKYvHPLCfxiBmGHgesqqCeOy10x+WcUCdYD2Jki7Iss\n" +
                        "bwu4CWgCZgFDCqlzcYZ4OPwOTMgbaHGKsP0Z4/QBOiK+G4BbCgrs5+FyBLgqa4AG4OdyhQWxZiXE\n" +
                        "KSQSmO+J9SnQM4vz6wmEjpCji8ctk9KwChicU6Av7vNpTklPGuDhCpDwoQOYkyNuLfC5J87dcQ4X\n" +
                        "AvsTCHycR1hOcSVkHr+Amz3+O+KMH0tJfFsVxEGOTxT3SUfRVLofnjgnzS72mdmGvOIK4oIctm94\n" +
                        "rjX95xcwOeVpPlmEJW58y4PPCuTwzUMnSKffnL8hnsb7eZMGOJzD9qSkpwvk2O655gZ2oCfwT8oT\n" +
                        "7VMgaWnMzIJdwDUFc/iKTLsl9+bmKHnRetDMfi2SWNLRDDZvSZpsZv6eLh3HPNdGAHU1koanOP9Y\n" +
                        "MKnM7LiklpjbH0lqNLM7zKyzaA5JF8Zcb6iRdEmK88VlJJak5ZLCb/5LSfea2Q1m9maZsSUprjhc\n" +
                        "XytpQIpzmvhEmFk7MC34eSha1ywHwFiVOg+HU5K2Sbpc0kABRzM0+BsrRaiSANYBm3CThat9Blnw\n" +
                        "ylngHgvc0mcd0Jpm+G1GgQ9UiXsS13Nw5Y/wGrEVuC/OYUtGcQAXVVlML2A8sABoIbk6sAlXRO5C\n" +
                        "raSDOfJ1Ag9JejdagyxIvkbSwMgxKDgPlZSnLDFJ0hhgoZmtkCTDVZGWRQw7JK2W9JPcIFkjqTE4\n" +
                        "SmiX9Imk7yWdCI7fgnNPuV2duKMuEJDWUxfFE2bWLGBw6NXuAeYBZ8zMcQ34/4I24GvglwSbGSXi\n" +
                        "W3C7pv18j4Fi67LuQCuR9R4wEHgOVwIJY1jJYBFwWYywNdXl34UO3K7t+Mj1xhie5wKPBjYnwze8\n" +
                        "W7S4Xqra2AcsiPCIwiswsJ0HJC+sgRH4v+njuG55cGAX/mR3BL/XAgdzCNoPrAbuj+Hiw/QE7o1S\n" +
                        "8hbWfEnRddwySc+YWXgROi7091dm1lXkAfrKbYPFbYVtk9RuZkVWHs8CB8zsQPSGmfnKD12k+nue\n" +
                        "1EyPXaPHLnP9HjeNmohrK6vxVL9wX1AcNmXNFQ74UiRIXCP21Q6Xx9guwe0vHAB+AP6IIbyQ0Mqf\n" +
                        "9Hbf5MuXJG5PyHlNjI3vrYHb+ekdsX0qhWAUu4GpuJ4yqbQPcbXKBHF1wNbAeXaMTdKg/mDIrhpj\n" +
                        "ZL63FxB7Fbg15l4amoFvulcTAI+TZRMkRoSvkddXgXQajpAwHBQGbkPxbGIlcGUaz6L72PUF/cpF\n" +
                        "i6QWM9uZxTjTP7ZFAUyRG5gnS+ruPfF2SRslvZNVVAmFxIWB2/CfKlfc7V9uvAB7JX0oaaOZbS0a\n" +
                        "pGxxYQCD5FbQw4LzUEm9guP80N8nJB0PnY/JTcV2SdpTcDp2Bv4F0JnG62CO9gUAAAAASUVORK5C\n" +
                        "YII=").bitmap,
                iconColor = 0xfff6ab00.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "虎牙直播",
                packageName = "com.huya.kiwi",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADcAAAAyCAYAAAD4FkP1AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAV2SURBVGiBzZp7iBVlGMafd9lSIlNaQ0sU2cp0MbAsIxU0ygt2Qa0NUwwr2IrUJDL9\n" +
                        "J9ggkIKykFC3iyUJYmVhFqbYdtss8bKaIhS5aka2EYWWZRG//vjmrNP4zfWcPfbAMHtm3svzzMx3\n" +
                        "e7+VMgJoBt4GlgKzgUuz+qbEnQKsAjqBlcDISsTNQ2AmZ+JvYD1wD1BXIOYVwMueuFRKpGUk0iZp\n" +
                        "dIJJp6S1kpaa2aGI7yhJYyT1Di6dJ6le0jhJfVNSzzWzF7JwLARgWszTjUNr6Pgrp68Pzd0pbn0F\n" +
                        "CJaLQgITP0tgtKS2Qk+l8phkZpvzONSk3L+9DDKVxgfA9XkcalPujyqDjCRtlrRX0k5JbWb2Xfgm\n" +
                        "MFyusxkhaaSka1PivQZMN7N9ZbECegB/FmgfbcBioKFAzonACtyYF4f3yhIWJBqbU9R24M6yE7vc\n" +
                        "A4AlwImYXOX1oMAjGUWdAhYBaZ94EQ4NwPKYvHPLCfxiBmGHgesqqCeOy10x+WcUCdYD2Jki7Iss\n" +
                        "bwu4CWgCZgFDCqlzcYZ4OPwOTMgbaHGKsP0Z4/QBOiK+G4BbCgrs5+FyBLgqa4AG4OdyhQWxZiXE\n" +
                        "KSQSmO+J9SnQM4vz6wmEjpCji8ctk9KwChicU6Av7vNpTklPGuDhCpDwoQOYkyNuLfC5J87dcQ4X\n" +
                        "AvsTCHycR1hOcSVkHr+Amz3+O+KMH0tJfFsVxEGOTxT3SUfRVLofnjgnzS72mdmGvOIK4oIctm94\n" +
                        "rjX95xcwOeVpPlmEJW58y4PPCuTwzUMnSKffnL8hnsb7eZMGOJzD9qSkpwvk2O655gZ2oCfwT8oT\n" +
                        "7VMgaWnMzIJdwDUFc/iKTLsl9+bmKHnRetDMfi2SWNLRDDZvSZpsZv6eLh3HPNdGAHU1koanOP9Y\n" +
                        "MKnM7LiklpjbH0lqNLM7zKyzaA5JF8Zcb6iRdEmK88VlJJak5ZLCb/5LSfea2Q1m9maZsSUprjhc\n" +
                        "XytpQIpzmvhEmFk7MC34eSha1ywHwFiVOg+HU5K2Sbpc0kABRzM0+BsrRaiSANYBm3CThat9Blnw\n" +
                        "ylngHgvc0mcd0Jpm+G1GgQ9UiXsS13Nw5Y/wGrEVuC/OYUtGcQAXVVlML2A8sABoIbk6sAlXRO5C\n" +
                        "raSDOfJ1Ag9JejdagyxIvkbSwMgxKDgPlZSnLDFJ0hhgoZmtkCTDVZGWRQw7JK2W9JPcIFkjqTE4\n" +
                        "SmiX9Imk7yWdCI7fgnNPuV2duKMuEJDWUxfFE2bWLGBw6NXuAeYBZ8zMcQ34/4I24GvglwSbGSXi\n" +
                        "W3C7pv18j4Fi67LuQCuR9R4wEHgOVwIJY1jJYBFwWYywNdXl34UO3K7t+Mj1xhie5wKPBjYnwze8\n" +
                        "W7S4Xqra2AcsiPCIwiswsJ0HJC+sgRH4v+njuG55cGAX/mR3BL/XAgdzCNoPrAbuj+Hiw/QE7o1S\n" +
                        "8hbWfEnRddwySc+YWXgROi7091dm1lXkAfrKbYPFbYVtk9RuZkVWHs8CB8zsQPSGmfnKD12k+nue\n" +
                        "1EyPXaPHLnP9HjeNmohrK6vxVL9wX1AcNmXNFQ74UiRIXCP21Q6Xx9guwe0vHAB+AP6IIbyQ0Mqf\n" +
                        "9Hbf5MuXJG5PyHlNjI3vrYHb+ekdsX0qhWAUu4GpuJ4yqbQPcbXKBHF1wNbAeXaMTdKg/mDIrhpj\n" +
                        "ZL63FxB7Fbg15l4amoFvulcTAI+TZRMkRoSvkddXgXQajpAwHBQGbkPxbGIlcGUaz6L72PUF/cpF\n" +
                        "i6QWM9uZxTjTP7ZFAUyRG5gnS+ruPfF2SRslvZNVVAmFxIWB2/CfKlfc7V9uvAB7JX0oaaOZbS0a\n" +
                        "pGxxYQCD5FbQw4LzUEm9guP80N8nJB0PnY/JTcV2SdpTcDp2Bv4F0JnG62CO9gUAAAAASUVORK5C\n" +
                        "YII=").bitmap,
                iconColor = 0xfff6ab00.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "云闪付",
                packageName = "com.unionpay",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAOuSURBVGiB7ZrRceIwEIa/Ze497iB0EF8FcBWEVHB0cEkF4Sq4dBCo4EgFIRVgKoip\n" +
                        "AKhg70GrsSCWMCQZnFz+GY0teyX2l1a7K2EhAlW9BHKgb9csJvvOWAMFMAMKEXlo1EpVc1Wda3sx\n" +
                        "V9V8H4nbU2t5AG5jJEan1uwIXHv9xZsTMG9ke+3DdxEpOla5P6kqr8M9gKjqAPh7YmVei6sOzrV+\n" +
                        "dOQdXJz46OiLqq44XbB7K6xFVfXUWrwFOvtFPga+iLQNn4bIt8S7BS6F3ocucG73S2AIDNgfn3rB\n" +
                        "/QS4xqXr5zWyvt874KK2t0gytmpAwLcfBO2eVbXbsN3Y2oyDZ6uIPjN7P4tljyn3O2vIpWsFYIOb\n" +
                        "xeeE/EJErlV1BHRFZBgQUeBGRO5ijVV1SE1umDKtYyL+wK6PwbMHnMn4/YPYdSoihapmQF9Epvb8\n" +
                        "XFVDswPYmGxoxluIEVkC5QEEdn/YY+JHXFVLgpEMSDziyHoiV1ZChZ9wAzukGpAtxIj0RaQxEVW9\n" +
                        "A37VkbC9TikiY7Pi3Np4ErkR8RhS7dHrF3YNYkTubYFNRKS0VD/WaQb8TJB4BEpV/SEi40BmSL1n\n" +
                        "+wP8wM1AczIxL6Cqaxu1lDcZm5xH30qm7hAjbDf3/Vmf4dZ6tKPL3PrIVLXUymtFt+OpgDgVkbU6\n" +
                        "L1GXHT+Z/Y9q3s1xMxG2y4G5Bu42AT+TPeCsgXwysnsXuGs2Hj0b4WnNuy715ENXvQ+59d1oixFb\n" +
                        "IwsRKez+t5Va2Bpa0MyWrzjMG/qIvjfDiBE5V9VLEXkQkWRgtFnx0x9zwx5l4HZD2Ytw/QRYWZtS\n" +
                        "XQyJWQdfG6u24VgiE7s+4ew4xBNpm55QrbvNkb//AscQWVAtWO9Kl1SESpyn8SQ3QQEX5HomV1h/\n" +
                        "i6D9hpeDsxeppDGGgu2IPKVKKWZB/QyXegysXuCy4tKUzXD51JhqP5Jbvf6AOoFjZsSPuE/ZC1wy\n" +
                        "2DUinuTInpW4k8wubpZyHMlbq3tSufV1SKx5FZEMN2q7mWiGI9Y35W+AS7bXS4GboX7Qz8AU7+HI\n" +
                        "7yafjXCs+/Ujl1GZWmF1XzxKk901Sf9ubfJL3IwebFbQrjhyh1snR6FNRF6F/z4gtg6fisibpQkn\n" +
                        "xKaDc4sfHUWH5gdxbcbs08yI/5+94IAzpJZhISK591rDpGi7MQRzv3bQED1gaDFugkOSCqkDsBYi\n" +
                        "nZepOyEsTq1lAoXWfOYkdWSMkP/XyX941ujE7x2wYfvDs7oDQf4B8kKbzST12qUAAAAASUVORK5C\n" +
                        "YII=").bitmap,
                iconColor = 0xffcf0002.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "中国工商银行",
                packageName = "com.icbc",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\n" +
                        "bWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\n" +
                        "bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\n" +
                        "eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2\n" +
                        "MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\n" +
                        "dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\n" +
                        "dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\n" +
                        "IiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS\n" +
                        "ZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD\n" +
                        "cmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJ\n" +
                        "RD0ieG1wLmlpZDoyMTU2MUMyOThGOEUxMUVDOTQ5NUZGMTVFMEJEOUZDNiIgeG1wTU06RG9jdW1l\n" +
                        "bnRJRD0ieG1wLmRpZDoyMTU2MUMyQThGOEUxMUVDOTQ5NUZGMTVFMEJEOUZDNiI+IDx4bXBNTTpE\n" +
                        "ZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjIxNTYxQzI3OEY4RTExRUM5NDk1\n" +
                        "RkYxNUUwQkQ5RkM2IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjIxNTYxQzI4OEY4RTExRUM5\n" +
                        "NDk1RkYxNUUwQkQ5RkM2Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBt\n" +
                        "ZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+h/0NYwAABW9JREFUeNrkmltsVFUUhqfTI9VSLF7oRYHG\n" +
                        "SmyAmFBUIDSkQaXGWi8xagzii7US5cWoiTG8mRAe5N1o9MWkr8SAFi+Z4CV9sII1YvASeaiNlRZJ\n" +
                        "L/Rip50Z/538myyX+5zZc+agUFfypT1nzl57rb3XXvtyTkWhUEglKGlQDa4GNWAlWM7fZsAEmAZ/\n" +
                        "glmQT6riIJWsVIEb6MB1IY6M828OzF1OjlwDNoFWsAPcCdYVKfMLOAG+BIPg23KdqigjtEwjPAUe\n" +
                        "BQ8aXTH1GAOOgsOgFyzG0wJHYvA4OFZIXo5Rd8k2ldoj68FB8FBEDxiFJ8EYOEeMrCJ14I4i5Y+A\n" +
                        "18APlyK0OsGbYK3jN5OBPgEfgG/AWQ7uWREqATOaGfwNYDPoAh28r+VX8DzoSzK09oFJRyiYe4dA\n" +
                        "C6iJERI1LHsoQv8+H10+lR0EC6qCedALNoJ0zHEmSVNXL3VLWaANZTliWmNRKR4HexMwPoy9rEPK\n" +
                        "YrGeiVK4C0wphUOg4xI6YelgXVKmaFNJjqwDI0rRKLs/9S+xkXVKGaFt3un3CCc5K/NgF2diI7eA\n" +
                        "e5kq8471ltUxpn5bDe4PKVfJeyb7DfOeWSl8yqWPlaNM/0WzVifIqvjsFr/fBk56TG5fgZtEubXg\n" +
                        "lEe5z0GTKNetxmmWNkaGlkmHGaW4Vz3zTAkzdZuKe195TNXZq37P6HSfVh30CNgpri+AA44Q8BET\n" +
                        "Ij+K6+9Bf8zF7AHaYmUnbXUWMLPrbrV0eBucVkpz6volrmQD4eg0nZgQz42Ah0ELV8x5MVbawesR\n" +
                        "dZymLS/bFQltPczVw99Ca6uajGY466aKhNaOBDJUl9LpWji20CY5KW91hdZdYJkKjWHPXWESO8ti\n" +
                        "MkybrCyjzRcVpJne2lTBDy92W7SMJ+DIhMczs7RJShttTweM1xvBFlVowNOIV8EZMUbS3JP3OXS0\n" +
                        "c6BWcXzYMbLes64B2mZXy1u4kv4jYBddC5pEgZ/AaIgynbV2hzz3Art+iNctnMxWeBgclhlHaVsr\n" +
                        "r5to+5RpvVoeGMjCv0fsoS94tp7ZRN2qNmUrPMuG1TFH26TDxvbagCcdtarAOW6MXGLi9B3wNNNk\n" +
                        "TqTMKoaqlajjnjkufSrVVPAW+CykzIzYcVoxtq8MuGOrcTiSi2it57gVtXEuJ9R31bY1bG54Fnwk\n" +
                        "d6v8ez7C+ZzDEWP78rjHQYFowUKMWd8+e5XDkSDOSUrA7pp2xHeYwlrOsk/4nAlEOPleSBnToy86\n" +
                        "bLL2rlL3zHMzaebwSYcj1SEVdXk6EXey7AZ3h/xW7XDE2D6R5j/nVQw3qkGrlfnIEFOllVM8XfGR\n" +
                        "6ohTzUY1Zoztk6arsiYPs+JmkfPrQ+aSnCMUfmbrV5Acs9tv4rkz3KzdwzCTE+LtYE9EHVbqaJts\n" +
                        "LGN7NmAaPMtZs1m0iJk1v/NovTdUy0fJCaJlm3IkTLap3hqg7Qtptsi8Y6/wgGcYNSaw1qr3DLdO\n" +
                        "da+ftuflYPyaYWbFnACu8aggiXccPjrW0CYrWdr8j42VGYwZHg7YFugBrxSpoJ0pWc7Q0zw6HXP0\n" +
                        "3ma+CMoJJ7Z7ONKjIiRDm52HD3tAXp0lbVDP9Hjuu/tBgyhnDhQGPcs+qercoM7Y8rQ1dM/+Pjgu\n" +
                        "rs0ib796JusZLttF8rCZcJNnWV3HfrXgPE5byzoOagZfeLSqOemoF+VWgwGPcn3g5lKPg+Ie0DVw\n" +
                        "bFQ4cr6dI/ocy/HrOWCjDugyYuFY1gHdFXlkuuQPsZfUa4Ul9aLninn1luTL0I/5MnRQvAydEVmt\n" +
                        "kttq+zK0lXub+5J4GbpkXk//bz8YuGw/4SjHkSXzUU2YU3Upv8+cxlIJfuaUtCP/2YdnfwkwABDy\n" +
                        "UyV45nFEAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffa90000.toInt(),
                contributorName = "fankes、naicfeng"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "中国农业银行",
                packageName = "com.android.bankabc",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASESURBVGiB7ZpfiBVVHMe/v1gDczMlXwLXooL+QC9LsLpqLiu5QS/6EIQPlhblQ0WQ\n" +
                        "WT21EiFlovgiFqyEPbmw+ZBYxKIW2IJuUQ9htCChLRQRIalsi3x6mLnL3Ht/Z+bMzF2S8AvDzD2/\n" +
                        "f9/vnJkzc85c6SZuLNh8JQaG3YJmbvsNAaAPeBuYyLQN047hjH0ijen7b1hnAGxNCc0hYysSksUE\n" +
                        "sLUOl1sqCtgEjEsakdSJM9onaQQYBzZVSVBaCHBY0pikwSoFCzAoaQw4CHSXCYwWAtwHXJD0bFl2\n" +
                        "FbBd0hdAf2xAlBDgEUlTku6pSKwK+pWIeSrGuVAI8LCkH3Jcvpe0K46bi12SzgVs3ZKOxojJFZKK\n" +
                        "OF5AYk1RkQisl/R+jr1QTFBIerN9pPDltNrMhs3s70KaBTCzy2b2hqQNkr4KuO0F7g/lyOuRPUqu\n" +
                        "01ZMWoIz8VTjYGZfmtk6Sd845h5J74RiXSHAdiUjh1fs0SokS+J5Sb857U8DL3sBbUKAOyS9FSjw\n" +
                        "YnVu8TCzHyW5hCXtSDk2weuRzZJWOO2HzezDGvxKwcxGJb3pmFYo4diELsexzUnShJltK8nllKQr\n" +
                        "khalv69IOlsyxz5Jz0h6qKV9s6SD2YamHgGekD+cHoitDBwCBtKfZ5UMGnvSYwEDwMmYXGb2j6RP\n" +
                        "HNOalGuQxG7njfUvYHEBee9Nt4HX0i2E3PkJ0AP86cTtzvq13iMDasenZnY5r1gBbk23SjCzi5KO\n" +
                        "OKYmrnNCgCWSVjoBx6qSSHEt3ergqNO2MuUsqblHvOfDdUmf1SRxTdLVmjl+DrTPnfiskKWO49dm\n" +
                        "dr0mido9Yma/S/rVMd3bOCgS0gl04tKS/F6ZE5J9jtQRckrSuoDtasYnFBuDKbUPRq6Q2ciEHroU\n" +
                        "npNcSvchu/dQ9uC9e3W1Hci/BmOxSOHRbUe6/yBg3xhZY5nTNse5U0Juy7HdXiM2i1wh2Zt92nGM\n" +
                        "fWVfGOlXJ/ZOp80V4vVIN7C2g2QqxQILJK12TO1CzGxG0oTjHLN+Nd89slbSAqf9dOOg9V3LW2jY\n" +
                        "0CEydWK9q+JEevIltQ99x9U+L+4HHjCzn3IKfSf/edA67ns+Uzl5lS44vOKYTuTFNVbJW3EoN8jP\n" +
                        "k7uIXSLPgcDrf9OKijfVHXHaXgBeLUuiLoBB+XP3/WaW25ONBOcCZ+GuEiRq9QiwEDjj5JgG7m71\n" +
                        "D61rhRYZxmOJdADvSlrltO83s1+is+T0StRZrdMjwN5A7Umg3GwT6AVmchIu77QQYDHwcaDmDNAb\n" +
                        "ig0umZrZt5KGAuZeSReB5/KIlQEwpOTS3RJwGUo5VS6wJXCGGhjF+aAZ2yPAgyQfRfMQEldazEsF\n" +
                        "hSB5/gw3ROUJAZYCTwJHgNmCvK93RERGzOPApQhBWVFtQoBjwB8ROWap+aU3T0wPMBYppg4mgfXz\n" +
                        "IqJF0Dbg/DwImAZ2UnaIrSlmGfBeB0Xsw3lix6L2f1HS4o8pmbdslLQkP6IJo5I+l3TSzC7U4dHx\n" +
                        "P9WQrMQvT7eedG9KZnPZ7XR2PnET/zf8C0KLidymNeDWAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff67c1a4.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "墨•状态栏歌词",
                packageName = "statusbar.lyric",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMoSURBVGiB7Zo7aBRBGMd/n1gpgkWiUaIIgo8IMSKCEosQEUFFBREFEaxSxFNsYiXk\n" +
                        "LH0QW2MhNqKdGjSYQrRREXygBB+QQkUQQfEBEbT5W9xt2Gw22b2d2b1D/FVzO7PffL+Z3ZvZvYP/\n" +
                        "NBbmO6CkdmAtsApYCLQAv4EPwE0zu++7T4DZrgEkrQe2Ad3AloTmNyPndgEbgSEze+WaSyYkrZc0\n" +
                        "qNroioqE6m5I6ixSoEnS6RoF0ogEnJY0P2+JA5LeZJRIKyJJzyXtyUui5CBQq0hAf9r8Ut3sku4B\n" +
                        "XYkN/VOWhJmdSmo4K6mBpGvURyKgLKmc1GhGkWqA/d5Syk5/ksy0ItXrOfU1WgD9kkrTVc40I40k\n" +
                        "EdAnaUlcRayIpBPU976YjqXAibiKKSKSVgJ9eWfkQEmVbdEk4mbkGNCUfz5O9EQPTBKR1AzsKyyd\n" +
                        "7PRUr5wJojOyD2guLh8ndoc/RFd2nzf4I+AhcAtYDCzwGBtgF3AmtkbSTw/7qa+SdqXJJMVeK4ll\n" +
                        "QaxZ4aDAPMdRGgO2m9mQY5y0tAWF8D3S7iHwUTN77CFOWtYEBZ8it83sjmOMWmkNCmGR5Y5Bzzme\n" +
                        "n4WFQSFxG9/gNIzIIl+BwiJ/HGMtznBOh2OfE4RFPjvG6s1wzsrkJjPyKyj4FOmUtDNtY0mHiGwz\n" +
                        "MvA2KIRFnjkGBTgvaVNSI0nrgAse+nsdF7zVcbsQ8F3S3hkkSpLee+prQxDXIp08BBJHNCVjVN71\n" +
                        "jlDZMLZQeUe81VP8YTPbEVsjqc/TSBXB4XDu0RlZBowCcz2NWl58Alab2Y/gwKQF0czeAVeKzioD\n" +
                        "Q2EJiF/ZLxaUjAtTHhOmiJjZUxpb5pKZDUcPxv70JqkVuAusyDurGvkCbDazt9GK2E2jmX0ETuad\n" +
                        "VQZ64yQSkXS2zl+xYQ46DYGkkXobSBpwkgjJXK2jxFkvEiGZcsECfyQd8SoRkjku6VsBEs8kdeci\n" +
                        "EZLpkHQ9J4FxVWZ+Tq4SEaFuSZc9Sgyq8pxSHyS1VUdxNEPyTyQNKOb3jlrx+qcaSW1U3v61UXkG\n" +
                        "aWby2/1x4AXwEnhQXXj/80/yFxO3SSfTTsqKAAAAAElFTkSuQmCC").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "车来了",
                packageName = "com.ygkj.chelaile.standard",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASRSURBVGiBxZlfiBVVHMe/v6hADXaj7S9amlpUD2V/sOzfJposLL6Y9ocSs1Ai6iEk\n" +
                        "qB52w9qKoFgQo6XQBMv2YVlNKSHaNaJe9gobthhlhujSS7qthFIPnx5mLp09O2fuzL0ze79wuXfO\n" +
                        "nPP9M+fOOWfOmAoEcK+kFZIWS1oUf8+SdMb5jEmqSKqYWaVI/YYAdACfAKfIjzHgDWBJMwMsAz6t\n" +
                        "w3wIg8DdMx3irQIDuPgX2ArMKTtAC7CvpBAuDgML8nizHCHul/RtDu5jkn6UNCrpKklXSlog6baM\n" +
                        "7Y9KusfMJnJopgNYlPFKDgIbgbYUrrnAerL17GSRIS4BjtQQPAg8UAd3Z4ZAfxQVZEeKyDngpQI0\n" +
                        "NgJH0y5UowIbUsjHgOWNhnC02oD+FL136yWeDxwPkO4FrigqhKebFuaOegi3BchOAQtLyOBqfxfQ\n" +
                        "/jAvUSswHiB7riT/rv7qQnoFeCZA8kWJ/n0PHwc8vJeH5ECAZG2J3n0P7QEPh7MSXAb8k0Dwfcne\n" +
                        "k7wMBcIs9utekND+PkkXJZTvdQS6E8iPAF2eiRWesW5gyDleS7Su+juu/4SnuS+QcZVfcGFCpY5A\n" +
                        "49+842FJh7yybuCQmQ1LapeU9H9ulyRgpaR+SQclfS1puaTdwA1m1h3XDa3trvMLkoLcGGj8k1/g\n" +
                        "CCo21yXpvFM0K8AlSQsl9ZjZa077TkmbHP4KcFLSXK9tq0+WFCQRZjbmlwGrYtI2SZfHxb87VWan\n" +
                        "UJ6X9CrRxDpgZl+a2X5J+4FrzexEXO9XFRkkAe3xJ4TTSu6Rs5JkZjuBixX1wLNARVKfmfU5IUKY\n" +
                        "FiTpZs+KYYshqUXS43H5hvj7FyX3yOnqj9j0nZLWKHr+6CXb7H3OL0gKciYD0RSY2aSZ7ZG0U/+P\n" +
                        "KMcUCAJcQ7TjUm0/YGZPSlon5x6JkbQcmuYxcxDg5qRy5/xSSasVbfdI0glJLxIvKeLv5yUdN7Nx\n" +
                        "SSuJdl+udmhuV3QBqpyXSpqXxWPSPRLqkVsck1WhIedwqaSvFA+5ZvYK0eJyBKgOx6OSNsf1+yQN\n" +
                        "SBqPz7cquqmfcjhvDXiZ9vibFOTnQOPrvWN3DpmQ9L6ZTZnAzGwd0C7pQUlvS/rBzCbjc+PAQ5Ie\n" +
                        "lTQ/bvKZmbn6jwS81H7QInq0TVqijAJp80LhiDV9/JmH4IPAGuflEn37Hh4LeOjPQ9IRIDlJzv2m\n" +
                        "egDMJlqDJcEf1WqShXY3dpfk39XuCmiP1EPWGSADeKEE/1Xd0HNI/t5wSNP2nJ4uOENVM4T8veGQ\n" +
                        "LgPOppBvKTBA6L6s4uFGBdbXENgO3NSgxqYaGsVcMGBLDaG/iF7Y+JNmGmdLHGCkBveuQkI4wm/W\n" +
                        "EKziALAZuItok28O0XA6D1gCrAF2ARMZuPYUGsIJ05sxTBEod5gnvN9UJIr9O6WE+bzEEDtmJIQT\n" +
                        "JrSJ1wg+mtEQTphvCgyRb4O6hDCDBYTY3tQQVRANp/ViW7P9TwHh9ylp6G2270QAPTlCZH890AyQ\n" +
                        "vMHto773gTONGmHeaba/XAiE6Wm2r7rghdnabD8NIQ7zetk6/wGsJc5dvdleEwAAAABJRU5ErkJg\n" +
                        "gg==").bitmap,
                iconColor = 0xff3e6deb.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "腾讯微云",
                packageName = "com.qq.qcloud",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAEMAAAAyCAYAAAAHtGYXAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAV5SURBVGiB3ZpviBVVGMafsyyWZSalxKbmZklmJJH9JSmtLC3TNaigMsoPsomhlAZZ\n" +
                        "oFEkUVoZoRuICbJhFFZgrR9C+2BJrObmgkXG0mqa/2s1xb78+jA7OXs958yZe2fuWg8Me/fM+77P\n" +
                        "c5577jDznjGqIoBxksZJukPSgJLjaOLYI+kbSdsltRlj9ldTZ2EAxgBNwD7KRxMwprfnUjYSJuSJ\n" +
                        "JuDq3p5bJgBP52xCEkeAF4Fze3ueqSD/1eDCNmBintpNnsUAMqYclHSg++8ISYPLoG00xjSVkVcc\n" +
                        "gJ8Dvs1WYCkw2VGjLzAamAE0AwcCV8mias/XCeCLABNmllG3FpgCfB1gyENFzC2r4GUpIl/IiWcO\n" +
                        "0HnWGgKMTxF3d858o4B2D99R4Lo8ObOIW+ERVtjvOMWQdUXx+gQN8QhaWAX+Ng//3KL5S8W85hDy\n" +
                        "E9C/CvwDPGbsBgYWrUHARcBjHiGNhYs4raXBo2NeUaSxAWuAwx4BMb4DFgDXFyKop7blDg07gNq8\n" +
                        "yZ4EOgIMsOFvYAlQn6uonvpGAccc/CuBy/IgqQdWlWlCKX4Hnshh7i6ti1P436Hcp13gQcpfDT48\n" +
                        "m7MPsd7BwPEA/vcymQLcBZwowIgYswsyZEsg//fAMFsNU1LwBkmfSbrUw3tY0iZJnZI6JO2SdFJS\n" +
                        "naQbJY3t/utCp6SxxpjdoRMNAbBS0ozA8M2SGowxh1zFaoGtKa6uJmCZEf3MWj113g2fZhjI3lBq\n" +
                        "Ac5zFZvrSdwJPJxR3EDgU0e9U8DQXFw4zXdzRjMAPrEVuhjY5Ug4CNxUpsB63BfiBRU70JNrUBlm\n" +
                        "AMwqLbTQEzylQpHzHXU3VzR7O1eXg+tRz/zagfOTRVocgW/nIPBeR+1dlda2cG23EXWfm+cxZH5c\n" +
                        "oL8jYD9weQ4CXfWPVVrbwmUzoytxfp1DSwcwqEbSbY7aa40xHTloHOIY/yuH2qUYbhnbl/j8sqQ/\n" +
                        "LDH1kmbVSLrdUfjLCoXFcF1823KqLym6gEq6wHJqb/zBGLNd0ipHieE1ilw5A8aYvMy4zzH+Q071\n" +
                        "Y9hWhdRzZUjSGle+y4xMG73Aou5vJjl2FfCRJFeTdn0WjgC4+q17k/8YY7YpuoMuxXBh3xAOXsLA\n" +
                        "iETexsThw8YMkwzV4eI842mZaE/mDNRKsrXHjmfQ8Wbi87jAnBUZ6qcCqFP0TGTDV5axvZYx1ch+\n" +
                        "db0kUMQjkrLelM00xqzNmJOGqZJsXa1WY8xvwVWwbwsG3QMQPbNkwXPBwsL198O9dfC6I8f6M3Gt\n" +
                        "jH7AyBQRi2T/NmxokfSUMWZJYHwWzJZ0jeOc69pUZx0FPnS4GrQtSNQMWu2o0Qy4buoqBtGD4B4H\n" +
                        "98eeHCsETHKc2wr0KWoieQB/j3a8I2eOI74tDvjVEVDM3kMOABo9RnzgyXM1nZ6PA1w76buBUVWb\n" +
                        "YSCAqR4jAKxtR2CmI/4EMDgOGg2cdAS2ny2GAH2A9SlGTHfkjvTkLC8NfsMT3A40VGXGDgDTSN++\n" +
                        "sG5tAkM9Occo/bKBYUSbPT68haPNXhSAycDnKboAljryfU0dgMUu4scDSLuA94E7CzQg3tsNMQGg\n" +
                        "1VJjOrAhJW8n8bVClrf9gGckLQvU/YuiR/GtivYi/n3l2RiTehdL1Hu8MHFcK+n+7iP0hm6DMWYi\n" +
                        "cGV3/j2SJki6IiXvkKQHjDFb4gHrq4/AS5JeCRTzX8UkY0xLcqDGFmWMeVVRH+LHaqiqMjZJGl9q\n" +
                        "RCqAOqJt/f8LvO+ZBb0hDIyVNE1Sg9zttbMVnZKaJTUbY3b4AjO9Lg2co8iQCZJukftpsbdxRFFT\n" +
                        "Z6MiE/4MSaro3XFgiKRbJY2W1E9Rdzo++lZSOxCnJHWVHBuMMd+WU+wf+MiWaF1CvSUAAAAASUVO\n" +
                        "RK5CYII=").bitmap,
                iconColor = 0xff4081ff.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "最右",
                packageName = "cn.xiaochuangkeji.tieba",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAe6SURBVGiBxVptbBxXFT1ndv0JNClR4vV7sw4hn+BCKgNJCm3apqE0bZUIIRUoJT+g\n" +
                        "IEqUKIhKRagpVYkSReKjolGhSKiV0hYVlaSENqFGog0OpAlFqhsQFZZj4p2ZXaV17KRys4497/LD\n" +
                        "s8nueHY8Xm/g/LHn3nPfO3fn7Xv3PpuYJUTE8jzvOpI3isgaAEsAZAG8P6CMAfAA5ACcEJETY2Nj\n" +
                        "3YsXLz4327kBgLUGDg4OLk6lUtsAfBnA/BmGT5DsFpEnlFIvkjS16phxAvl8/kPGmJ0A7q4lPgL/\n" +
                        "IvmgUmp/LcGJBYiIlc/nt4nILgAttUw2DQ6mUqlvZDKZMzMJSpTA6dOnr06n088CuK0mackxKCKb\n" +
                        "bNt+I2nAtAk4jmOT7AbwkVlJS47zxphbs9ns8STk2AQKhcICY8xfRWRxfbQlxoiIrLZt+9/TEa1q\n" +
                        "joGBgWZjzO//D+IBYC7J5/r6+pqmI1ZNoLGxcY+IrKqvrkiMAdhD8jaSr5TZr21paXlouuDIJeR5\n" +
                        "3g0i8ud6KYxBbyqVuieTyfwDABzHWUPyWJm/CGCZ1jpXbYApb0BEKCKP1V9reBrZPTIysqokHgBI\n" +
                        "3hDiNQP4XtxAU96A67obAfyuLjKjMUTybqVUd19fX1Nra+tOAHcGvhUR/HcbGxv1/Pnz340aLB02\n" +
                        "kNwuInXUW4FjInKX1tpxHGclgKcBXBPBuwigMfj9A+Pj4xsBPBM1YMUSGhwcVCJyUz0VlyAij+bz\n" +
                        "+Rtt23Ycx/kWyeMkw+INyc1KqVbf97WIHAliN1QbtyKBVCp1O+pT31wCyXGSX7Vt+zttbW1px3H2\n" +
                        "kfw5gKgt8pBSah9Jv6OjwxORbYF9bbXxKxIQkarEGnEWwC1Kqac9z+uwLOsoyXti+BXrXERGg1+z\n" +
                        "AwMDc6MCKhIg2TkruSGIyH1KqR7XdbtE5DiArpD/FIAuEfltYNrkum5X4Eun0+kdJW5TU1PkgVqx\n" +
                        "XFzXHQLwwTrm0EPysIg8CKA1wv+a1vq6fD7faYwpbac+gF4ACkCmjHur1vqP4QHCCVyx7ScGHVrr\n" +
                        "nOu6pwAsqkYi+Xml1Athe9VSYqYQkSMk15LcjMlPMRFIfiqIfyuO5/v+xSh7+BzwAaSSTl6Gx7XW\n" +
                        "20j6AHpc190CYHWSQBHRAGBZ1pm484fkcJQ9/AbOJtN7CQbANq31lkA8XNddDuBjSQcQkXPBzymH\n" +
                        "ajnS6XR/pD303I/kDfoFAF/SWh/s7++f09zc/BSA5QCWYWZvsVTzL4/hnKnWaoYTOAlgTYJJz5O8\n" +
                        "Qyl1NGjyXwLw0QRxYRS01q8PDQ1dVSwWPx7D+0s1R/gcSFJCvyMia5VSRx3HWWmMOYbaxIPkYyQn\n" +
                        "isXiXbhc+0TxDlfzVSTg+/4hABMxcxaMMets2+71PG9tkHAmhh+Hv4+Ojv44WPv3x/B8y7KqVscV\n" +
                        "CWSz2bMAXqrCfSdotk86jrNORP4A4Koyfw6Am1B8r2VZG5YuXTqWz+cfQvz6Pxx31TLlHCD5kwje\n" +
                        "eQAl8TeTfBGhuyGSXQ0NDStI/hSTnVQUigB+RPLT7e3tb3ue9xUR2VGFW8Iv45yRlafrut0APltm\n" +
                        "ekRr/YNcLrfasqxuVH7yAAAR2aG13kXS9Pf3z2lpablDRFaSnCMiIyRPjo+PH1q4cOFwcEn2gIjs\n" +
                        "RPxh2qeUWhF39RiZgOM4y0i+iaDkJZkXkedJbhaROYHgU5ZlHRWRTyL4EpM8YYx5RGv9Mskp3yUR\n" +
                        "sVzXvZ3kwwA+ESO8xL/Ptu1fxHGq1v6e531TRJ6IDCJfHR4e/lxnZ+dFEUl5nrcfwMYyytskjwB4\n" +
                        "S0TOY7JAXATgJgBt0wkPkBsZGVnS2dkZWUJMmwAAuK67F8CWCNcmrfXB0oPnedeLSE9CYUmxRWv9\n" +
                        "+HSk2GJOKbUVwN6wXUSuDj3XswQHJne8J5MQYxMgKVrrrQC2YrLRLtl/WCgUrgGAQqGwCMCeWYiN\n" +
                        "wq+z2eyFJMRE5bTWeq9lWV24fKRnfd8/6Xme5/t+P6KvQ2oGyZNJuYn7gfb29n9qra8nuQFBIiLS\n" +
                        "jjpfAgTjxtVFFah58lwut5TkFwGsJ7kG0bcMteIigDujWsgw6vLpiUjacZxFJJeRXCAicy3LagJQ\n" +
                        "FBEfwMOYea9tADyZTqd3tbW1napGqvvrD6NQKLwv+J4k3f/DEACvAHjBGHMkm82+We68ogkEdf5+\n" +
                        "ALfMIOwggFWoUuWSfFUpdXPpuW5NfRiu636mWCweR6X41zHZyZUgACoOKxE50NzcvJzk9zFZ4SLk\n" +
                        "/3D5c2wfOlMERdp6Y8x2AOH7zDeMMV+3LKu3zHYCwMsAvl1me2/evHnnAewGsDu4BF5JcgHJcwAO\n" +
                        "XJEE8vn8Bs/zfgZgCTllZf6qoaFh+8TExNbQzcM+AEMhbsWzbdu9mLzoikTdlpAx5jeY/DeDSxCR\n" +
                        "v4nIOq31vRcuXGgQke+WfCTzqVTqKZIVGtLpdNUdJwp1ewMicoDkFwA4JP8E4Bmt9dEyYXsAzCsL\n" +
                        "uT+TyYwG22/JVmhra/vPTOatWwK2bW8GsDnKF6zje8tMzymlngUAy7KuLVtWz5Oc0fXmFduFQtiI\n" +
                        "y1v2cZJfKznKdhVJpVKR/Ucc/icJBD10j4g82tDQsF4p9V6Z7zVMXmk+UP4Hv6T4L2IgDYbl0+yk\n" +
                        "AAAAAElFTkSuQmCC").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "学习通",
                packageName = "com.chaoxing.mobile",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAARYSURBVGiB7ZltiBVVHMafE4j0AuW6pr1gQlHZi0GGtomyRbAJ+yHZTCLTam2FTD9E\n" +
                        "mRWx+8GIkBayIpLFdqEXKIWS0O11i8B2qSQkhIiytFDbkrQIIuLXh/lPnT17Zu7c7t2798N9vpwz\n" +
                        "z/+ZM//n/uecOTNXaqCBBuoewDmVjnFKNRKpAi6pdIB6MSKgZ7JzqBhAKwnaJzuXiuAZ+bHIfAFa\n" +
                        "a5FX2fCMAOzK0MwA7gWGap1fYQRGALqD2AterD6rIUWNECSfon6rIWUaiaGuq9EGvFHARP1VA1gI\n" +
                        "9AI/FKxEfVUDWAG8VUby9VMNYBqwAfjUkvqMZE78VKaZrskycCrwMHDIM9BlsfOAbcDvZqpolS6u\n" +
                        "tYk7gS+8BLYYPwV4DPgVeAeYZvwm4M8CRqIPy4kw0ALs8S58FLjDYrcC+4zvMe5SYIdxQ5HEn2X8\n" +
                        "gvDIRJvoBE54F/wAmAfMBQY8frXplwOHjdseMbLGG/s0YCWw02JLJ8pEb5BEPzDVkv3Z41s9Eyl2\n" +
                        "GecbebDE9S4AZlXbxMuBid5IsgArjO/yuK+Nu9/jtlQ1wYImtgfJPmV8d8BvNb7H40atand53NuT\n" +
                        "YWJ3kOxgJFmAF43f6HF/Ae3ADR53FJhXaxPhxPzY+DUB/6bxNwf8AyTPmb0et7bWJvqDpPYBTcDl\n" +
                        "wC9BbDEwC/jS44ZsHH+BOABMraWJ8N7/G7jaYq8EsXTS9wV8mxn0kbtKVdtEC8m97eMZi60K+IPA\n" +
                        "uYx/z3jJ9Ds87jAwo5ZGwv3QbyQPu5nAV0FsnZ3zXMDfDlwYcH21NLGB8XjSYk8H/B9AM8muN9zh\n" +
                        "NgHLAq42u1qS/dCx4OLHSJ6us60yPnbaeR0Bny7PTwR8VV6YinxpfFTS2QH3vHPue0kdks4IYoPW\n" +
                        "3hLww9bOKSvDgsg1QrI5WxnQJyUNWL8jctq71l6VMeyZwXH4Q/wvlKrI3RFuwDl3ELhe0qIgtt85\n" +
                        "9531Z2eM+W1wPL9EDoWQaQRYovG3hyS9au1NkVi6CZwu6fSMoQ8Exy0lciyEvIrEqnHcOfeJ9WN/\n" +
                        "BRyxNqsakvS6pFHvuK0aEz5qBLhG0upIaMTrx+bAyaD10S5JzrlRJWZ8hPOwbGRV5NoMfkSSgGbF\n" +
                        "V5+ZkuSc+0b/VSfFfGCB9UMjnVT4/0iWkawJeNzaKzLi/lvbcCS+TpKccx9K2hbEuqngFTbLSNY9\n" +
                        "mxqZnhGf6/X3R+KrgHSR2CTp8yC+uxIzYwDMIRtLTbM2R3ORaRZmxD8CppjmxgzN+nLzjlVkQYRL\n" +
                        "4axtztEsliTn3IjGLg4plkjabJr3JN0T0WwFXgMKL80xI1fm6JusPZGjWeb1BzM0G7GPEc65PkkP\n" +
                        "RTTLJe0leQE7P+d6cTD29TPEfaa5LUcDsMgbbzhHt97TdQJHMnSjJJ9Yw53Ev3AhQf6X7/edc5tJ\n" +
                        "JmzsV0xxyDmXfoi7TtLjpcY07WWmPStHL0n9zrmBEpoGGmjAwz/KTMTXMyluSgAAAABJRU5ErkJg\n" +
                        "gg==").bitmap,
                iconColor = 0xffcf0003.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "波洞",
                packageName = "com.tencent.nijigen",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAALQSURBVGiB7Zo9aBRBGIbfT7QzIsEQoolgpYUIASEgCAoSjcRGsBIEFSy0EbERBSOK\n" +
                        "XTr/UBQlgohFRBAiRPwDjaawMInRwsagGBMQBMHqsdgNhJDd2/nZuxPugeE45r6fd77dvdmZkRo0\n" +
                        "KAUr0znQKqlLUnvaOtLPWUkzaRuXNCnpk5n9KTMfJ4BO4ATwDDemgavAjloL6AUeOSafxRvgJLDi\n" +
                        "fxWwkFFgZzVE3CxJwEL6yhKwBvhYJRGFxDg/tYAuSSPeoyB9k3Rd0of0+yZJZwva9pnZuYDYCWkl\n" +
                        "QhgB2jJ8F+FxVm5LHEQ0SRry0D+fC2b2PaOvyH0wldVRWIikO5I2Ovx+McZy+l4UsH8bFB04FnhJ\n" +
                        "zbE8J0ZLBdv7oSKaiPeE2pIT52iO3e0gEWmAU5FEAAzmxJnNsLkSQ0QzMBVRCCSzgF6gLW27gbGM\n" +
                        "3/YHi0iF7I8swoXzLrkurdDfEzAOIZw2s4vRvJF93ZbJ8WgCUhHbaiDiiG++eZdWu69TTw6Y2YCv\n" +
                        "cd4/ezWFHA4RIeUL6Qhx7MCwmd0KdVIPFfkSw4nLpLEs/sZwkickc8ocmfUxnOQJ+RojQAG6gUOh\n" +
                        "TuqhIpJ0DdgT4qBehCyTdJeAxbncxQdgWlKLr3MPfkjaa2avXQ0rPbUe+OXjTauSynS6GlYS8tQv\n" +
                        "nyDWKRGzIZpHYCXxX6yKMgqsLZprbkXM7Jeky8Ej4sdmJZWJc48Sd/HBhyfkrL7MUXGKYma/JV2K\n" +
                        "Mip+dEsaAOJMp4ChGlYF4EZefoUXsYFVkt6r+i9c89luZs8X6yhcLjObUVLmusRnW2GrpJcl5FKJ\n" +
                        "z2aWOVN2voHM7JWkZkmLlrhEzpTmGeivwk0+C+wrTcQ8MWVuht4DQrcyaipoGDjoEj/6yQeSPcYe\n" +
                        "SbuUnHooyqSkQUkPzeyda9yyj3CsVjJnWniE42fapiVNSBo3s4kyc2nQwJN/DSiEAjz9qlQAAAAA\n" +
                        "SUVORK5CYII=").bitmap,
                iconColor = 0xffef0025.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "哔哩哔哩漫画",
                packageName = "com.bilibili.comic",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAKcSURBVGiB7ZnPSxRhHIef79APoowg6hAVglR4sPDaJSHwEHUo6NKpk5co/4CgrkJ0\n" +
                        "yQTxlN4sKugYhh2sTqXrSTBFwoyKIujHwYJPhx0jx3dmZ2fm3VHZB5Zlv/PO+36ed2Z2Z/aFJk28\n" +
                        "YLUaSNoCHAd+A0tm9sV7quq4e4EDwFZg2sz+ZOmkW9KApAlJy1rNoqSbkjo9hO8M+16MjLkcZhmQ\n" +
                        "1J22s0Gl47ukngIlesI+0zAY3d8inX0C9tWZ4ZqZ9TuCdbkam9lzR9urwJ06x/1sZvvXVCUNpZwN\n" +
                        "FxcltUu6Lmk8RfvxsG17uG9Whv5NUChxHnhU54ysFy6Y2WOTtBOoAG1lJ8rIHHAiADrYuBJQzd6x\n" +
                        "IrLRaYqsNzoCYLrsFAUwHQAzZacogJkAmC07RQHMBmY2BoyWnSQHo2Y2FoQf1twrbSD6AQIAM3sB\n" +
                        "9JUaJxt9YfbVSBrJcQPXaEYSFSX1SpovO2UC85J6Ux0vSefKTpvAGVfmwFUEjqQyLodDrmKcyFGP\n" +
                        "QfJy0FWMEznmMUheDruKcSLrmU0j8t5V3PQiCx6D5GXJVYwTmfAYJC91HZE3HoPkpeIqOkXMbBKY\n" +
                        "8honG2Nm5nx+SrrYhz2FycO9uA2xywqS9gCTQKuPRBn4CLSZ2U/XxtgjYmbfSJiBEngQJwE1Fnok\n" +
                        "7QJeUv5fRm+B02b2Lq5B4g+imf0AbhedKgM3kiRSI+lhic8fd9NkrLmG+J9MhepaYiOZonpKfa3V\n" +
                        "MLUIgKRfwI6sqerkGXDZzBa99C7pfgNOp2FJ27wIRGSueJS45V0gInNSxX4JPJF0tqESEaFLkl6V\n" +
                        "LVDXxZ6EpFagCzgVvsfd2iwAr1deZva0iPELE4kiaTvQAuwO31uAOTP74GvMJk088Be/qJb9kyEC\n" +
                        "bwAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffd3618a.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "航旅纵横",
                packageName = "com.umetrip.android.msky.app",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAL2SURBVGiB7ZhNiBRHGIafWgVBVNwlBFQURSV/CyJ6SBBkRVEUBTUqIeTgIZhLogiC\n" +
                        "iIfNKYJnEcwxIIKgBwV/UBCEgIIH0VX04CWIhl0vJuAhl8fD1MS2HXument3ZqBfKLq7qr73e9/6\n" +
                        "6ekaqFGjRhFCSmfV5AQhtMyh3gJGquACGEjU1bOojfQaaiO9htpIr6Gvjai/qtfVpX3/gxg1Xe73\n" +
                        "Gdkbb3f0rRF1PvB987kvjairgavAzlj1eypBMgq4bpXhUo+p/2Sqr6izWiU4ETv82WtG1Jst6j4F\n" +
                        "mJkjvwjsio9nO5qm6cXG3PPKEMI4ZIy0GL05U61qEngErAshvG5WDACoV1p0Pqiumi5lCTgRQhjO\n" +
                        "mgBAHSlYkxPqbuNmKrGmW+4R9UvL7ZGvP+Zu5scaIj4BLgB/qTfTBu5/0duAz4CvgGFgJTCUQPEA\n" +
                        "OB1CONMu0ZoyIz3FeKP+oW6OGveptzsZtSNdlf0Ol9Sf1aXqgHpIHWs2djR/NmbmThfEX1N/VBdH\n" +
                        "HQvU4+qzfMci/e99Taqzgf3ApljmdjQKneM/YCyWO8CFEMK4OgRszZSWe6jo76DCz3h1BFiSK4M0\n" +
                        "DM6J17nADOA1MAG8ylxfAW+Ah8BYCOFphnsI+Bb4AVhfpGPSRqYS7ZZKKxQZaff67QjqAmARsDB3\n" +
                        "XRRC2FJFjnZIMqL+RkNktgymJlXnpca0Q1eOuuom4EYVXE1062C1tmrCbhkZrpow1ci/k00YXwwb\n" +
                        "SoROFDWmGnlZQkAeP9F4SaTiUVFjqpEXqdnV5Zn7EWA0lSPicVFjqpEyx9/NmfvxEvFNXJ1E7PtQ\n" +
                        "l5X4KHyiDmY4zpfguFSZiYyQYyWEjGbiV6t/J8Zvr9xIFDNawszhTPxsdbzDuL1FWqowc7CEmf05\n" +
                        "jqJlNjHlJjJCflFfJpo5qg5kOA6o93J9bqtrpsVERsgK9ZT6PMHMXXVPjucb9bD6nY0DXhIqPY/Y\n" +
                        "+J34PJYv4nVJQcg54GQI4X6VOmrUqPEh3gK0HUzGOkCsyQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xff64ac32.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "米游社",
                packageName = "com.mihoyo.hyperion",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAaoSURBVGiB7VlrbBxXGT1nZuys7DiJFQo0beM2JVBUIrkCtbSoUkOkKqIE6MNqI0Ky\n" +
                        "vte7CgkPAaqgP5BWIB5SW6ISYSPHM2ulPFpSyVAIVekPkwaKIiqgFEUVFImu3RdR0ojEIbZn7+GH\n" +
                        "x5Kxd+2Z9db90/Nndufc+e45c+937zczwNt4G8sC38rOrbXbANwN4KOSLgPwy3Pnzu08cuRINW2M\n" +
                        "4E1TtwiMMVtJfk9SN0kAmADwDIBb1qxZsx3A0bSxvDdJY1309fXdTfIpSd0A/kZyn+/7V0ZRtA3A\n" +
                        "XwGszRJvRUfAGLPBOReS9EkeCMPwy3N5kh3OuYksMVd0BCTtAtAuaXS++AStnufFWWKu6AiQvDk5\n" +
                        "PliLlxQAmM4Sc6VzoCs5vlSH90i2ZAm40gYuJekknalFSlLWgJmnULFYvLRarRYws35fI+nJjRs3\n" +
                        "fqJUKi05d0nmJF0AcK4OX3XOZdKUuvHu3bvXB0Hw7TiOe0m2SDpBMgJgK5VKN4BnU4RZDeBUe3v7\n" +
                        "VC2S5EWS69JqAlJOoUKhcG0QBH8BUATwJ+dcTxRFHw7DsICZ3fySlP1NYZHdX9KEpM6UsQCkGIFS\n" +
                        "qeSNjY2NkLzcOfdAuVy+d5az1l4vCc6582k6k/RfkrmJiYlWAJPzec/zTjnn3pXFwJIjMD4+vg3A\n" +
                        "Zkmjc8UDwOx89TwvbfKdldQGoKMWKellkhtTxgKQwoCkW5PF4WANzs/SGcnXAXi+79ebJi9JujJL\n" +
                        "zDQ5cF1yHMsSuBYkVQDAOffuOk1eJNl0A1cBEIBXFlzseXFSTbal6UzSC8nP99Tifd8fldT0Ym49\n" +
                        "gLPOuQWbj6RJAMiwez6bXHftfKJYLK6N47jX87zfpIwFIIUBSTlJrw8PD19ccHEQnHfOASlL4Fwu\n" +
                        "9/Tk5ORFkjcA4J49ezZ5nreV5G1xHK8hOep5Xm9TDZAUgJq7LMkzkmKSqfaBgYGBN6y1X5H0DWPM\n" +
                        "CZIVAL+uVqufHR4efi2L8FmkGYHJpEpcgA0bNpypVCqnJNVLygUIw7AfQH8GjYsiTQ6cJdmRz+dz\n" +
                        "86dRqVRyxpiXSV6VpVNrbRfJmyR1S3qvpCtIvhPAapKdmHnEfE3ScUn3l8vlk8sx8G8AV7S2tnYC\n" +
                        "eLUG/y8A71siBq21N0u6C8BtkjYBwGzxmaxkmHOuneTVJK8GcE+hUOg5dOjQr2oFXnIZJTkGwIvj\n" +
                        "+LI6/LF6m48xpqOvr2+/MeYFAMdIfp7kJkkVSY+Q/BqAOzzP+1AQBF0k10dRxFwu14GZ3b8MIOec\n" +
                        "e9QYU/MmpcmBf5KEpC7UqDglbSH5n7nnisXiO6anp78KoOCcm12hjpM8Lumxcrn858X67O/vPw/g\n" +
                        "RQCmt7fXA7AHwACAbZjZk9IbIPl8Mqz/dwestV2SBgF80Dl3JwCUSqWgUql8IY7jr5Nclxh/guTh\n" +
                        "MAwfWaqvWmhpaSnEcXwDya3W2l1hGD48l09TC/0+maM3zZ4zxlhJJwFcDIJgS7lcPpbP57eMjY39\n" +
                        "EcCDANYB+DnJG6Mo+lij4gFgcHBwWtIBSZD0nX379q2ey6d6M2etfR7A5QB2AviMpO2e531xaGjo\n" +
                        "R4mhvQAOAMiR/IOk+6MoGmlUdB0NT0jaDuC7URTdN3s+7RPZXufcT0j+zPO8x33f/8Dg4OCrPT09\n" +
                        "fkdHx0MA9iftymEYmmYKn4WkbwHYTvJL+Xw+Gh4e/gewjHejifiHSe5McmRXFEU/biSWMeYYyVNh\n" +
                        "GN61RLsIQC/JX4Rh+ClgGW8lkju/E8BZAN2Nik+wWdId+Xx+0VI6juN7Sb4C4JPGmFuBBg1Ya+9B\n" +
                        "Mm0k3RJF0XONxJmDEQAMguDTizU6fPjwaUk/TEb8oWKx2JLZQLFYXAvg+8nfzzVBPAD8NFly+5Zq\n" +
                        "GEXRNwGMArgmjuP9mQ1Uq9W9ki4BcDSKoh80ILaWqN9JetI5l6oiJXkg+Xlf5iQ2xpwAcL3v+zvq\n" +
                        "1ScrAWttWdKdjeTA+wHgwoULT9cijTG/tdY+vix1KdDW1raX5MlGDOQAYGpqasETWoJNknZYa2s+\n" +
                        "9zYLBw8enCT5QCMGTgNAZ2fn+jr8CABI2t2ouLQYGhp6LLMBSacBoFqt1jQg6dGkdtq1PHnpkNmA\n" +
                        "7/t/T5a8zbX4crn8jKSjkup9A2gqMr9ed849R/J2kt1Ipst8RFH08WUrS4lGcmD2YeS6RVutEDIb\n" +
                        "WLVq1XFJVUk7SqXSW/KdeS4yGxgYGHgDySiMj4/f2HRFGdFQMSfpqeR4e3PlZEejBkYAOEkfabKe\n" +
                        "zPgfrqzeQT35+oQAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff6ba8e8.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "原神",
                packageName = "com.miHoYo.Yuanshen",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAA2OSURBVHic7Z158BxFFce/LwIhQLgCVDRACFcghCNcxiCHyBkOEUEoFRAorRKVS1BK\n" +
                        "Dks5RJBLQAuRKMQbRFCEkIgQQE4TOcQoV0KAHCRASEKAQPLxj+5h+ze/npmd3dn9sdZ+q7Z2pvv1\n" +
                        "e6/f7PRMv36vV+qiiy666KKLLrrooosuuuiirbC+VqBZABtKGi5pZ0kflrSapNX994qSlkl6W9JC\n" +
                        "Sa9K+pekP5rZK32icKcD2AI4Cfgt8DSNYTFwRF/3ReqQOwDYRdIhkj4taZMKWW9oZi9WyK80VuhL\n" +
                        "4XkABks6WNKXJe2QQfZvSdMkTZf0vKT/yA03b8oNPSZpTUn7STpD0odS7Q+S9OOqde9oABsAFwNL\n" +
                        "MoaPKcDpwDYl+T4b4XVyq/rRcQC2BC4H3o0Yaj5wpR+KGuU/P8J3bJV96EgAKwNXZPzanwe+Bqze\n" +
                        "pIyjMvgPqqofHQngeODFiGGe80brV5Gc8yMy7qyCd8cCGBcxytvA2fUaHhgDnAlsWkC3EbA0JWtk\n" +
                        "NT3pMHhj/D1i/NuADUryut+3fQ/4VAHtU4GsHzbXiw4FMBZ4M2L8ht5GgAkpPt8BjgFO9J+PBrT3\n" +
                        "eZqZ1fWogwCcEzH8K8COTfD8XYRniEsD2lm+LGtO8f8L4KcR40wFPtwk3x/lGH8OsJan+4gvu7Ka\n" +
                        "HnUQgGsjxvkH0PQsHHg05wJ8NqDbGjipWXkdh4xf6MSKeP8tx/iLgf5VyOlY4B6GaTxTAV/DuSry\n" +
                        "MB9YsYp+dCT8LR/7VTY98wRWLTB+go2q6EurUbk31P/yYsPM/mb2agUi3pJzTX9M0iJJL0naSNIJ\n" +
                        "ktYL6AZJmlGBvM4C8RnuT9ogdyhuQpagYcddxwLYLGPoWbMBXgOAs4A7gbPqbLMT8I6Xu3H5HrQf\n" +
                        "VQ9BP4iUjTezBfUyAIZKOkLSKZIG++J9gEMlnSfpdjN7O9bWzB7FrRPsbGbPA6tIOkBuaFpL0kBJ\n" +
                        "yK0NvyJpmpk9Uq9uH2gAgzMehn+oo+22wJeAvwLLCh6uc3GTuEcI3vUz+H69gBfADOD3Xv5W1Vmk\n" +
                        "PlR5BxyXUX4o8FVJd0haLhetsK6k7SRtIfcw3bqEnPX8Z4LckuT7ADaT9Ekv41lJ36yD31D/Odzz\n" +
                        "uEXSZWZ2bwmd+h7EPZytwM3AmJTs0cCfKpZzIx3yHJEktcAAId4DxpNy3AErAJe2UO5SOsWFARzW\n" +
                        "AgM8CnyZyDoBbqlyegtkxvCbvrBpaQAPVdDZZ3G/6jEZMsYC/6xATln8GUiHtXywgPPTTGygc3fh\n" +
                        "lhYzQ02Aw3Fe1L5EJY7EEC2JjAOOknSUpO3lXAIJFkiaJecieEbSQ5IeNrPpGXxGyAVPHSdp81bo\n" +
                        "2gD2NbOJknt9lvQfM3unUWYtDU0EBkjaWNI6kuZJmmlmiwvabCIXyXa4pN1bqV+DeMzMRkkScJOk\n" +
                        "pWb2uUaZ9XlsKLCepF0kjZS0m6S9+lajujDczJ4G9pI0SdIIM5vW10qVArA37mH6Vh+N583g7KAf\n" +
                        "r+LCKNfL628WKgl8KgvgMDmX9XaSVu4LHZpEODReI2mApMdpIMCg7RfAG//GdsutGEOC47n+e7Ck\n" +
                        "R4Asl0wUbb0AwN7qfONL0mrUovfWCMpN0nW4mNbrgIOLGLUtPwDYXPGVsk7EANVeYIZH6of5z5NF\n" +
                        "jNp5B9zdYv7RNYIWYaGZLfPHm2XQPGdmlxcxassFwMVifqSFIp6WtI2kOS2UEeLfkgSsIWlUBs0D\n" +
                        "9TBq+QUAhkj6RovFjDezZ+TeSKrG8kjZ3/z3Xsoexp+rh3k77oBftkFGYvgr5HLDqsQ8SZ+SdE9Q\n" +
                        "dqv/vkcuPfZEuby0EFXrUR7ADm2YFN2Qkvn7ivm/GPD+lS87J9LX9YEng3Z9n/wXKNxKbJ6SuVfF\n" +
                        "/CcAKwKHeP43+PKpwDEp2asAj/v6Ke20dS94pVvtZugV0YBziaezYZrBiZ4vwOX++K6gfgJBxB8w\n" +
                        "kFp80sD2WTwF4IgKjZCFHQJ5VwKn+eObK5QxDFgpOB/rZYRZ+nOB3QJdDvDlX2i/5WtKXF2hEWKY\n" +
                        "mJIH8IQ/ruriv+n57ZoqN+JhOLsH+vyLOkJywg6sTIW3DPBwBQaI5QwnGBHISuJ/pvnzVSmOL6oH\n" +
                        "Ezy/U1PlU3z5aZE2W/q6dYGd/PH2FIXLA78AKpmtAmvTM06zERwK/Dyj7u5A1hrUjP0yYL78kSbl\n" +
                        "gx9CcBEZaRzs69KBAXOBdVL2+DlFabEBg6YTG4ARTXZ8qufz14z6zQNZYaLeMrxfHpew1wyW4B1u\n" +
                        "xLPs5/u6UZG6p1L2uMSX75plsOODxpdVcAEOb7Lz23k+sWHkokDOvqm6Jbh4UAFbNanDrZ7P6Bya\n" +
                        "IzxNLFXqlkDPu33Za8BKMYOl991Zt8kLkJc+VITkQTomUjctkLE6tUjoBHek9GgmbmiM53FjpG4p\n" +
                        "8AywwNMMzeBxLfC5VNm4tLFis9VrmzD+3g12OMEBns9XInWDAzkzIvXDcL/8JDvyqgZ1uN+3D990\n" +
                        "nsCl1U4G1qGWIruPp72L+jEqNFhWvtUWDV6AOeX62gMvBXyuSdXtE9Ql4/6pwNG+D8lM9XEgCbRt\n" +
                        "dCjcwref5M+P8+cD/HeYgnWnLyvz3JsYGuwvGUR1efNSxs96aNaLbwe8JgflBwflF/iyXn54akPB\n" +
                        "9f58eAM6/My3HebPe0VY0zMOdgmwcoEtYxiZMLsvh+iCOg2/JeVuwSyMCni+jtvWYP+g7EBP9/0M\n" +
                        "Pdb39QuDspd82XiKHXXL8G+BuDDLhRlyPpFq9xlfvnOJvl6VMEvvt5DG6BzDDwG+W0JoHuYRxF7i\n" +
                        "jD0kOE/eanKj0IDHPN1If367P18TuKhAhwN9mz38eS+vZyAnvNvHB+VP9uIax+ykQXqsTeMNIuml\n" +
                        "1Jd9Uga3pGUEsvamtoXZJQUXINn86Vv+/HrgMX+c92M72dMM8H0GP5PNkHN60HZ2UB7Lj87CCAFH\n" +
                        "1kH4RESBvPfjRhDdxwHYM0W3X8EFOM7T3eTPh+FdLMB/M2SHgVZ3+rL5BXK+kOKRuCD6AYvq7PNX\n" +
                        "+km6TdJ7ecIkbU1q4cPMHpLLz60qlafXRqq4xLy7UsUvF/BJcpG3kyQzm25mi3Az5GER+mPN7Fwv\n" +
                        "7xxJydvW0wVy0uvPo7285ZLG9yaPYvt+Plh2XCGpdBTBLNQLe8HMdpfbWvJeSTPlFD9X5UNQZocn\n" +
                        "uMWOtDfxPdUCobKQzDQ3IZg3SBojt5NughmSdjWzX3h5Z0j6blD/RoGc9FpxeHFvLmibwAUq4CYW\n" +
                        "9TrPrs7ihneE+eMpdfJLsG/QNivj8l0KtjvA7aqbYGBQnqxkLcbtIdcvqIsll99WIOfYFH34IB5I\n" +
                        "fYtCD/STJDObL7ewXA9OwL3O9XLamRlegUHyQ0AJvBUcZ0VRrKD4MBJiqP9eJulYavOFWZIulrS2\n" +
                        "mZ1pZstxr41TJB0b4VOUDZPec27t5MDMFkm6L6PdSarlU/eMiwX+UMdVSzAd+HRMArBJCT4J9gza\n" +
                        "581NckNcqN15iSOvV5Idbt24aE7wbI6M/rio6BCTUzTfy+B7na9fDLweYz4Ot3thvbgJWD/FY2SJ\n" +
                        "9gk+X+cFmJpjmIMi9E/hduy6COeXn1lCp+jGrrgNBtNYil8S9TQHRGgAklHiJGBWQryaV36FgMGq\n" +
                        "uMzHvF2pEizERYklbTct0ckE5wXti2bVv8b7ZYI2WZuzNoO3cL6mZHa8B3BvQZujPe22OTSHJfyS\n" +
                        "1aP+kh6UtJWkKXJhdQ9KWizpVNVezfJwvpmdlVxQuYCmMrH/15vZF33765SdeZ9gjtcVuedC1jYD\n" +
                        "syXtJBfFvL+kEXL/MzBQLjBtiaTX5N6eDsngMVcu9nRoRn0PWjMbjNsPb1YGzTgzO75HCc0vYCyk\n" +
                        "55tF2YzGqUHb3ZvUJcQVdRgttrjTMDy/VXJI3p87vW8wM3tKzcVwDpT09eD80ZLtR+GXE81sslL7\n" +
                        "QDSBJXXS1eV4rAMv+O+1c2iG4/1ePWJDzexSScdI6v10zsc8Sbeo52RqcgZtHrYMjnN3QimBPYoI\n" +
                        "cKtf21ckL1nSXSeHZrnc0Jmp0CDcitQkXLbHItxr3Zu45cvbcYveRwI7knogeh4rAQtK3r0Xpnic\n" +
                        "XtiiPhyZ09dtgdkVybkn4HtgDl25jcNxYSZDgLzbKtYutmt5HhaQ2rib2gy2WZwS6o97yzuG5sNn\n" +
                        "EjxIMDklPyqjnm10mgdu4bwsei22ACcDyxvglcYiXLzQ3cRDTRrF9yI6355DX3oLt4YBnNFAh9aP\n" +
                        "8BmO2zDjg4SJBDGhga6rkR3V96X2WL6nQmVfSR/O4fVx+v5CTCKIA43oGNsrG3IcmS0Fztv6eslO\n" +
                        "nlDAc2dcNMQLDZuxHP4JXEjGNjqBXumFmgTnV2vVksANIWXzBQr/ZAe3ArUj7p83bqa2CN8s5uPu\n" +
                        "tPPIWZpM6ZIebpfiArsyt8xv62YduJjOSZI2LNFstJllDkkRGR+S+7O3wXLv4oPk/tpwAzkXxFqS\n" +
                        "+sv1/V25RZ45cn9xOFduJ96XJb1kZum8ryLZR8q5K+bJLfhMM7PZ+a3aDGAtyqUuvU0YSdZFNcD5\n" +
                        "e35D7/jOLOQ+EzoVffZXht7fMxnnNRwraU+5oWNd1YYI5Nwis9RzxayLLrrooosuuuiiiy666KJj\n" +
                        "8T/bBoEzDb4zxQAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "崩坏3",
                packageName = "com.miHoYo.enterprise.NGHSoD",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAqLSURBVHic7ZxbjFXVGcf/axhQECxSsF6Qetd6qaJ4GZFamUYx3tvGFxvF2Ae1vmnS\n" +
                        "S/TFpqVJTZMWYzSp+qJteKhyM9WYqVaNYAQEKhgRkXJxEEZKCjPcZvj1YX+Hs854zll771nnnJl0\n" +
                        "/5KTvc+31/7W/1t777XX7RypoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoLEAc4FZwCkRfV4LbAPe\n" +
                        "Au6L5bfZAPdZDNuAayP6PcXKfG67pBe8A32SNkn6bNBnk6TNzrnDKfMYJelU+6z38xhhXCHpOttv\n" +
                        "S3sSMFrS6ZLOlHTWoM+ZksaV0rZL2i9prH0fJ+ki+wymH9gqaaOqXCTn3L4aekalFT4M8bXjHwDG\n" +
                        "q3rhni3pNCVlG6KvXdIhlS9APdolnWGfrwF8KWm6c65b0oB3aHwK38MVXzuSBJws6UNJ34rg/3Cb\n" +
                        "pL0RHEmJoNG2f8Czp350hyG+9v22Ha04hS9Je9sk9UVyJpUf2V7PdkJE/83G114qp5hVal/sC1B6\n" +
                        "ZHd7tpF8ASZ6+1/ZNmaVur9N0n8jOizxlbc/qQH+m8U3vf3dNVPlZ0+7pJ0ZTzokaamklyV9IumI\n" +
                        "pD2S+iVtlyTnXD/QI2mykqboSKWkvcc512/76yV9W0mjZKKS98R5kn4o6RZJYzL4726X9HlGUWMs\n" +
                        "szskfSBpoaTFzrn1g9JtU3IBxgLHO+ca8aQ1DOAbKrcOt5fszrkBSVuACyTdoKQcrlC+xsYWAfcT\n" +
                        "h43AY14Ar3rHLs5fFK0BuNjT/6pnf8xijcH9bZLWRdJ8lqRfAaU7YZN37PRIeTQTX/MmSbLYfqkk\n" +
                        "1hisb5P0L1V2nIbCWJU7ahs8+0i/ABs827gqafMwIGltm3OuV9JHkZxK0gW2/cyznR/Rf7PwNZdi\n" +
                        "uTCi/3XOud52SXLOXUoytjHFPpO9zxRJJ3rfZ6j+m/67kpao8qJ+J6LwZuFrLsUSepcdkrRCUo99\n" +
                        "dkra5X3vse+76oyd1Qd4I/BiWeCl3W227lyZtRCg27Tv9mwLArG/kTWfPE2ndwPHL/H219r2JCDW\n" +
                        "+EnDMa0n2de13qFLqiT3eSdrXnkuwPLA8XOACba/0rNfliOvVuFrXSkdHX4+J3BeqGy+Rp4LsDJw\n" +
                        "vE1Jx0SSlnn2y3Pk1Sqme/ulGK5UuLxWZc0o8wVwzvUo3Hu+yrb+HTGSnoAZ3n4phisD53xuZZOJ\n" +
                        "vGP1oSt9tSQ557ZJ2mq2aHOqTaCkdavFIFlMdQjVDFXJewHeCxyf5fWIu2w7BTgvZ35NwzROsa9d\n" +
                        "ZmuT9L3AqcsCx6uS9wKEWkInqNxi8JtmI+EpmOntl7RfovC8RqhMqpL3AqyQ9EUgzfW27VJ5Qnt2\n" +
                        "zvyaSadtUfnpvb5G2hJfKCmTzOS6AM65I0rmA+pxnaX9UskktiTNAYbtKgnTNse+fmjaJen7gVNf\n" +
                        "tjLJTMXSCaBd0oQaaQezVNLDdY7PAtpM2FIlraBJkjqU83FtAh0qz+AtkY7W/7MC5y0B0k697vUm\n" +
                        "dyoBJgH9Kcey03CZ+b3Ms81LKbTpAPMC2odKP1AxRVtRBTnndivn27wGPzC/qyRtMdutEf3HpqRt\n" +
                        "i2mWLIZILLMyPkq1d8DSiBl2evsLbXshw3CGzDSVhptf8Q51VkmelyVphJwT4VHbCMwHOj2/Hd7x\n" +
                        "YVcNAb/x9HV49k6L5bMI5RIaSzqa6dqMjg8CXcAjQNXJF8ABmy39ZsodtZYDtAGfm7ZNgKuR7nyL\n" +
                        "sctizsKaaj5rLSD9m8KTDz4HJZ0s6TZJ15CsEy1NPvQoWTXRC/xV0i+ULOvoVGUnrZV0qjwFucA5\n" +
                        "B3CcknhKk1KTlSxJLH0/qGxLUELN9jJUrgiIwdwqfl/KIL6hAC95ui4229zIZVBtxXldUasjZv5P\n" +
                        "z+8asx0kWWncUoCTKVcnqz37WxHjX10r/3r18LMR45wFnGn7f7btGEkPRcwjLw+pXJU8J0mmNTT4\n" +
                        "loXsZQkcD+yLeBfMM7+TgD6z7QSy1KNRAcYAX5qWPqyTRGWHbKjsI1lll0vgcxGF7MAKG3jes98T\n" +
                        "sUyzxnePp+N5s402rbF4bigCp6fM5ADQmyLdXVX8Zp7GiwWwytMx3Wx3pYhjn8Wchun1NFRt7w4S\n" +
                        "uUjJKuEvlCxS7Vay8HaHktmuHc65XcAcSX8PuFvunOswv10qD0/f5Jx7LaQlJoP0vumcm232ZQrP\n" +
                        "ft3knHsNmKJk9cRptp2qpDl+qqRTJG1zzt3RCP1VAd5OcUdcbWlv8GxNHx0F3vXyv9FsV6fQ/3az\n" +
                        "taYGmJkigCVe+vcGF0KTdN7o5fueZ1+cQv/Mer5bDrAwEMAR4HJLO9uzr6LGEEBkfY7Kur9U9Vxu\n" +
                        "2uqxMOS/5ZAM5oXGSRZ76Rd59ruboO8nXn6vePbQ3X+ItINprQZ4MhAM2KMMnAHsN9s2khVojdI1\n" +
                        "3vLA8jzD7Nem0Ptko3RFh6QT1x0IaBlW5QBPePaGDVVT2cF6wmyOyndRNbqB4xulqyEA96a4q35s\n" +
                        "acdR/tnPAeDcBug5l3LbfSMwzuw/SqHz3th6Go7dWcsCgW0CjrX0sym/BN8k4gvZtLxpvo9gE0XA\n" +
                        "saahHkef1BEHSY83NMn/uJf+Gc9+f0QdP/X8PuPZHw9oGwBm1PM9rAGmAR8EgvyHl34C5TvyP8DU\n" +
                        "CBqmmi/M9wTvWFdA24fAtKFqaCrARSQ/5VyR4u76Lcl/6/jndwCHLc3rDOHxJ6l6Xjdfh/Hmeu34\n" +
                        "aNMwENC6wmLKNqnSDEjmU2cCvwc+DQRSYjuVE/Yn4N3tFmyJeou/Qtp+5vnxf8M8FW8hFcnE+/aU\n" +
                        "2j+1WGfSynltE/As2YduFwNnA7cCfyDplQ4An5DMwwoYRfnO7SPHnUfyJJbmHV7HlkQCxwEbLM9V\n" +
                        "puFW05RmKMJnh5VB84cngJ6MYrFzVlL7xfy0538y8G+zr8OajSm1jbNzMB+TvWNP18i737Tliit2\n" +
                        "+aYJ8uEcQtNwu5fHDMrzDC9m0PaindOL14oBbic83pOH3NVkbkiqiVUhZTn4Cq/lAdxG+Yl5IIWu\n" +
                        "ByxtP3CbZ59mvmOzklat+CYZQTwcEJiHd/yggEfNfhC4po6eaygPBD7q2UeZz9gcxkZ2Wwbw6wYE\n" +
                        "Bt77wPJ5CthDnXWlJOuO9gBPeTZH7Xp/qDzRyLJNBcnKgjUhpTmZ5+XjgEtT6LkUr+9A3BUOPmto\n" +
                        "4YqOCkjuvDST8iX2An8BdqVI++AQdD2Ywv9O07I3g/5ehtsKb8J//DRA8j8Tc7Exf+AqwheuH7gz\n" +
                        "h547CY9D9QJXWfrxpu0Nwj3jaONUUQFeqCL2Y+Dn1BhTAW4hPIN2ELg5g46bU/q8pcb500zzx1XO\n" +
                        "G75/wwyMBd4nqVrmk3IkEbibcPv8ACkee5LqMLRm5wgppz9J+iLzSaqq94E0/zLcOoCJDBpgS3ne\n" +
                        "I4FCWwcck8LPMcBHAV+PhvxU8TsamBhOOYKhdotlgEEjmQE/HdSux3/XyBhGNCQdpkVVCu1POXz9\n" +
                        "sYqfRQzj3ykPC0gG0pZ7hbaZHCslSFo1mz0/y8kwoPd/DXAisNUKbk74jJp+SivgtgInhc8oOApJ\n" +
                        "a2Z+BD/zGW6dpoKCgoKCgoKCgoKCgoKCgoLW8D/lHzfLsLwPJwAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米运动",
                packageName = "com.xiaomi.hm.health",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAARUSURBVGiB7ZpNaF1FFMd/0zYfpKVNiShBBdu0Ra27oHaloYIIBbHgxtI0KliwCxVE\n" +
                        "dCOIC1Hc6aKl3XSjm3bnyoWLurMQEEy00qZGqtCmIqGxMU0e+bmYiXm5vOS9e3PvyyZ/CIF5M+f8\n" +
                        "z3ycOefMhU1sohKEKoSqW4ATwEGgOzXPAbeACWAcmAwhzJelsypDdgKjwH3AvdTcBfQAHUANuAF8\n" +
                        "B1wAvg8h3GsgqmXkNkR9AXgTmAE+CSH8vEq/3cAWYGnWO4FdwF5gEDgMHAK2A78AXwJfhRDu5uWU\n" +
                        "G+oxddZl/Kr2rENev3pKHVMX1XH1SJmcGyk9rv7rSsyrB0uQ3aEOq1fVmnpW7S2Dd1bRKw2MUL2u\n" +
                        "7ihRT596Jk3QZXVvWbKXjJhtYMQt9VBpipb1BXVEnUkTtb8MoY22k+pN9akSeK+le0i9nYwpvjLp\n" +
                        "YK9mxNMlcl6Lw1BamcuFzoz60irb6WYV26kJl5F0Zs7lHbhV/XEjVyLDJyQHUDOPa1a3Gf159mA/\n" +
                        "WSHfZpz6jK553DxeUn0tba1F9c92b6dVOA0nPifzDnxcfVF9oCJuuWC8NMfSX9dG81kXjOFMTX1+\n" +
                        "o7msC8bYbFo9m/1tXWG8uhV4F9jdpOvfwDchhCt1Y7uBD4jh/R3g8xBCrQWd3wL7gMdKy2fUTnWi\n" +
                        "gavOYtF4ub6jhjR2Z5pd1T+SYa3ofD/dKwfq27c0IPZgKVauRCBmip8Cz6xT1iiwjZh9/o9tmU6v\n" +
                        "Ax+pT4QQ/sqpYA74EPgn094LvAX0E7fRCHApp+x6XAcWgIH6xqwhA0mZBRTUgPONJkC9RkxpAR4q\n" +
                        "ILse08AssOJayBrSTcyxCx8itQ84T5yQKeBV4Lei8hpggchxxZnKGlIGOon5eA/wO5lzWBWySuaI\n" +
                        "M9nZDuUF0UHkOFffmDXkFnEmd7WJVBH0EjlO1TdmDZkgWlxerlw+9hA5XqtvzBoyTvQ+g20iVQSD\n" +
                        "RI7j9Y3Zwz5JrAAeBj5rQWgN+Ji4FReAu8TJeS/JnmG5qvh2GnMj/Z8jhiidxLtnoUVDnksyJtfs\n" +
                        "ZawpTav9LQpuG9YKGhu5xgvADuBo5czy4yiR28WmPdUu9aeUwHRUTq1FFEqs1DdSxDpcMb+WUSjV\n" +
                        "VbenRP9qCjk2FIWLD2nwkZRWnlnKITYCFi0HZYScS0nMSMn88nA4YZECXUZIr7FcOaMOlcivVf1D\n" +
                        "6h2LlkwzwgaMheTb7TQmGTGVdA80H9Ga0P1J4IyxFlvZmUln4oTLzwoHmo/Kp2BvWuJ59XQV3ix5\n" +
                        "p9MuP/SUsxINFPWmEKaW3OFwGZdmuuyOW/XTWwPFR5JPX0w37akisZklPoYW3uvpYjpGrJA8Soxg\n" +
                        "fyC+nY8S8/RplqPaDmJStIeVz9M7gCvAF8DXIYRsFaZaQ5aQ4p5ngZeJIfbDxBB+gVjtaPbBwEXg\n" +
                        "Uts/GFgLaifwCLF4tg+4n5WfcEwRs9AxSv6EYxObqAj/Ab6iKXBgVe9kAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffef5000.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "钉钉",
                packageName = "com.alibaba.android.rimet",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAYXSURBVHic3Zw/bBRHFMZ/Y7lyikQEITkpIImU4lISglCc0uSPhGlIQWPaiIICuihJ\n" +
                        "EclpIqIEyfRJRGU3EAXBQUmEiO10QYIEcm6ChIjPbmi/FG/2vL7bu9u9e3t7xyedtPbezcx+++bN\n" +
                        "+zcTGCEkzQJvAW8CR4EDwBvx9n7g5Xi9AzyL1/8AD4EHwGPgUQjhyajGHMruQNIc8B5wDDgCHByy\n" +
                        "yU3gV+ARcCOEcH/I9kYPSbOSzktal9RUeWhKqktalDRT9XP3haSapCVJjRJJ6YaG7KXMVs1DByTN\n" +
                        "xMGVKS150ZC0WDUnLcjEuwqJ6Yd1mf6rjJgZScsVk5AHS1WQU5O9oUlBXaPSTbIpNQ66pigaKnvK\n" +
                        "yRTxJKMpaaHIM+c2FCWdBy4WZnU8cTKEcC3PF3MR9IKRkyAXSX0JktkUP7oMabywDZwIIdzp9aWe\n" +
                        "BEmqAb8BrzgObJywCRzr5fxOdbshWxav8+KSA+Y495wdXQkCviCf570BXMZEdhIxH3VsfkhaKLB0\n" +
                        "LsbfJP7YOLodeVDL4qJDgmRhg0sF+NwPEEJ4HkL4DqgBJzHJmiR8n/XPrCn2GcWCWq+m/4hEXQsh\n" +
                        "vAt8AKwWaKtKzKufESkLdBV1I1b69axq40RFsK5egTfZQxRFo2ejnS9gUePt6GbHkjSY9CQo7ARK\n" +
                        "mpO0MkSfZWE9Pc60DjrN4DbPx0V/EEK4E0L4FFPqF4BbA/btjcPKeuEaTuwbHiOTSdWSqpeqlfaB\n" +
                        "1RwaLRRG6EPUjExX1VUNWXv1qnziPOs9nnkYspIU0orDGItgIT2IulOjyyoxPyWT9FGtgsvpN+Qt\n" +
                        "xksqObwpI2tZ5dlWZhPJFGNZWJeRlennOJJVhg/YlFTzjjMvaHcatOuMFlkqYRrKFPsghm4vzAXZ\n" +
                        "XDvrNM530sUEkYhDwEfx83a89RBYA+4Bt0MIz536946AngmyNf+UU4MXokffFbJA3DxW/gJWpfEM\n" +
                        "K235w4MsSfXYx7D4JsiW58MOjQFshhAOFfmBTD/tA7biv5rD1v84vvTVaWI8xwkHJS3kTakAZExJ\n" +
                        "DxxxauflKXarurxwadAHjbGkoaaYzMAbtkirhV4x6UHRNxBeMr5ybGtfGQQBnFIFVRVRerz0KWAS\n" +
                        "tOPZYAqfy1yYUdboFIml58HWtHOD7ZjHYr2bmK5LqleTFSt5Oekq1q1BCjNlqRs33ROxM40N2Lvh\n" +
                        "diTt72Dk/IXZP3/HTzN1vzDiovDlkGPMwtNprA7Zdd5mYDVGD8vCt5STAd6ewsR7YhENTS9XqR0P\n" +
                        "prC5XzZOqWh6Nz8yE35OeDyFKcZR4KI3SdEx9fC5srANPEoCZqNM6C0XIKCrRa5yAn1p1AGmomO4\n" +
                        "NjzhuXFWFhvqCPLLYjpzshhVHUuDd8Mlyi3NWQNI7KC7+IU88uAwcFXSBnAT+A/L8X/I3hX166wf\n" +
                        "y4zPssd7D2KFmSxGc5/xKpbaBl7Pcl61u61qq+3Wn059b8TiC5OgEMITSbcZrRT1w1o3zz6qhT0x\n" +
                        "I/kuADeTi7Sz+rNjBx64kfeLUZ95VuFeSS7SBN3GihrHBbkIkhmKVx37XU37gi2Cojh7e8PDYJ8s\n" +
                        "A5J8uu21+Mm53x+63onL7LgWOXXEl+S/26je3seegFmUIs+InCfupf+QWdHePliHWZFZSC7fTIcX\n" +
                        "XkuyHbLp9q9z+5kRh24h1/HZ0mjYaEsF/eLc/iZwLutGJkFRi19wHsQwaNklMl/OW7rPDZSLk19Z\n" +
                        "zLBYiONZLKHtnsmFfptZZjE/reyQbD+8hMW0vfXOrRDC8V5fyLMd6kXd8bOBbYfqObX65sWiPjrj\n" +
                        "NaoxwTawmEfv5Eocxlz7SSZ3R08am8D7eVNLuTOrkaQTjJe/VhQbwCdF8m6FT3+JOuk61SvuorgF\n" +
                        "nCm6nBfOzUf2jzE5u3gALocQjo/y3CGgVTxZdVV8LzTkWOA+KEk1jb7IOw+WNU5H5cgqXMfB8l5R\n" +
                        "lae+9IOqIapZFjGlnWEWB3saq2YtK3SSpI2ulHWWWemHvEErqH4UK648wuBuyyaW0LsL/N7v1AQP\n" +
                        "jISgNLT3qMD98foAncWkO8BTduuIthjxEYEA/wNNd2byIsHysgAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xff4998fd.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "剪映",
                packageName = "com.lemon.lv",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAK6SURBVGiB7Zo9axRRFIbfY4yCdoqFiUisrFLlD2hvm0Zs/C6iRMUiq2iCIivYBC1M\n" +
                        "kWifyn+QOmAawSJVVtFtVFRIE6I8FjO77HzsfNzZTCZxHljYuTP3nPdl5t45M3ekmpodwfrtAJ5J\n" +
                        "GpE0Vp6cRFqS2mb2MG5nrBFgQ9UxEKZlZmfCjQfCDcB7VdeEJI35GgMEjABNSROlSXJnwtfaJXxG\n" +
                        "TpcopigBrWEjIyUKKUpAa2SM7FVqI1WjNlI1XIysSrok6bgVQNJ5SW9Scv110CcBKyTzwilwcs7J\n" +
                        "mDy/gQfAXIKWFVcjkbJggGbu+jnawHRPe2YjB3Pk+xhK/lLSuKP2lqQFM1v1tzckXTezRcd4uYxs\n" +
                        "dP4AZyXddk3qMyxvvMnM3oV3Ao8kPc4azGnWMrN1SdckfXXp77Md1wjMAFuSnijheSmM8/RrZktm\n" +
                        "dkrSLUk/cnRdkDRqZpd7G4H7wKakpqRDrro6wdJmrRXgXJ++94DNhL7zwLGYftPAz5S8qYM9r5Es\n" +
                        "hhrAds+xz4GjMcdNAd8cDAzUSBZDs0BkMgFu4E2zRRmokVRDPbGvAJ8GYKCbszf+vqm1AlDOpXWT\n" +
                        "Cl1ae36w78T0ewf4VaaRuT59poDvOQS8BiIvOfBviKUbAa4CX3IkDvO2j4YGsJXXiNOshVc0Lkoa\n" +
                        "denvMxzXaGZNMzssr2Aka7A81W9XtJmtA69UsIzvbAAXJR3pLePN7CkwJGk2d/SUS+uDo+gseWf8\n" +
                        "HM4PVnkurXFgeWDqfYBJeRWvJJ2UNI//qFskaJb7yDLeQD9R0MAFYCkl15+sZyTPGOkw6f8Emcei\n" +
                        "K0NZD9w3tVZtpGrURqpG2EhrV1S4EdAaNtIuUUhRAlojL8Co9hp7h8hae2SM+AeslSYpP2txHwwk\n" +
                        "fcLRlLcEXJWV3rakz2bW2G0hNf8V/wCyU7nz5UJdKgAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "贝壳找房",
                packageName = "com.lianjia.beike",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAOJSURBVGiB3ZpPaBVXGMXP92pwKwUJIVUiFjEphhbj04WoILWFulFMwWUXgksVXXQj\n" +
                        "6sKNCwXjwoDYismi1HbhxoJgXQXEF0VjC5ZgRKlKoQsLZiP+XMy8Moxv7ps7cydj/MGDl8z9znfO\n" +
                        "/OG+uTMmB0BT0gZJzfiz1jW+Au5K+l3SLUktM3voVQ00geu8f/wCrM8b4nDdbnNwPu3bUiHwOnQ1\n" +
                        "Y2b/+2+0vwA/1WOnOMDh9neL/zEqadEFidloZrfaR+RgrVbKcVKSDPhC0nTNZsqysSHp87pdBGBD\n" +
                        "Q9GEVyVzkmYr7tFsSNpcgfDfko5JGjazVWb2qaRBScfjbaFpqorJCujL6gj0AeOhm4YOciDvLgQO\n" +
                        "hWwcKsifwA7f8wH4BngYwkCIID8DKxxm1wCrHdsHgKtlTZQNcsJhsAcYS4wdA3oc40+VMVI0yEtg\n" +
                        "r8PUOjrfBlwH1jnqvgP+W6ggU0DmJAqMAk8d9U+Jfttl1Y8At6sOcgFY6jBx1EPrqENnKXDJx5hP\n" +
                        "kEOOxr3ApE/jmEmg16F7JK9QniBPgK8czbYC0wVCtJkGtjr0v449lA7ypaPJfmC+RIg288B+R5/d\n" +
                        "3QS6BbmRIWzAmQAB0pwBLKPnDVdhniPycUpwELgWwHQW14DBVM/l3YryBDmXOsSPSxrNw2Ngd6Lv\n" +
                        "sW4FBrlWTv6R1CNpWY6xIXku6aWkNd0G5g3y3rOkQM23io7QDknfh7Wj45KeSfpXvqs6nufuaKr2\n" +
                        "su/J72AipT3qU+wbZEWq2TDwylOjE6+A4ZT2gI9AQ358lPzDzO5JOuup0YmzsVZxPPfcxQ71/cCM\n" +
                        "p06SGaC/g+5FH5EiP+PfWT4ChigWZgYY6qDndVpBsSAT6caJMC0PnRYdQsRa3neLRe8Q92QY6CFa\n" +
                        "HXnkqH0Uj+l420u0Q577Gio6Ic5L2mVmv2WY6ZW0XdKQosd1ryXdl/SXpJtm9iIrhKL54zNvR77J\n" +
                        "E7wANnk3zPZR9DoDyq+izAIjAUKMlAkRIkibH4FtBQJsi2tLI+BOCKGYK8BOYKXD/CfxmCsB+84Y\n" +
                        "cFpS7jVbD54purgfSHqj6KJfK+mdyS8AF5YoehhfBX3xZ0tF+kmmGpJaC9CoaqYa8WsRv9btpAQ/\n" +
                        "mNkf7cfT6yXdrtlQUVaZ2VxDksysJWm8ZkNF2Gdmc9KH+ApHYsORBXfkz3gyRCYswtecnKlYRC+e\n" +
                        "vQX8J+XSR1NWBQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xff2f77ff.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "huazz145专用推送",
                packageName = "com.huazz145push",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAHlSURBVGiB7ZqtbhtBFIW/syqrZFKFlAW3oKQ/KDXKGwQF5AlaXhSzqq9QGCmopLwk\n" +
                        "CqoTEhIWJSwkUkHU4FPgdeVsbGt2Z+2ZXfmTVpbGnjvn2Ds71zNXBGJ7COwBb4Gt8noe2j+QB+Cu\n" +
                        "vM6BH5JOoqPaHtj+ZPvM6TgrNQyamhjZvk5ooMq17VETE7kSZsZ5m5iy3Iztw9QKa3DYBxNT/v8y\n" +
                        "Kk0MgAtgO3Aa5cIN8EbSfVE2HNA9EzDRfAAwNbKfTks0+wCy/R74nVhMLB8KJmlH19krgJ3UKlpg\n" +
                        "p2CS/HWdLdn+S/tZ7Lp5kG3X6BCTUr8AXpavobGHwdHrLKMNxM8bb1EuN6p8blhHW7FowFUhaQTs\n" +
                        "th137UYAJP0CvrYZ81lM5+rtsIBbSd/ntP8EvsSMP0uUEeAjARPS9mdJryvNV5FjP2Jdt9Yr299m\n" +
                        "GyT9aXOAdc6Rd6sM3qaRE1Ugbt2pRZKn1irYGMmNjZHc2BjJjY2R3OiNkdi/utXMN+b9SyYnVcv6\n" +
                        "L6Q3mw8FT7+FLnLXKyOnqVW0wGl/NrEljYFxaiURjCWNp+vIcVIpcRxD347eJN0DR2k1NeKo1P6Y\n" +
                        "JfuyOTL/eHrGTBeOqYOrH3I206gepdtFNTNmOlPmpBqmsi48+wccWLnZenD1xAAAAABJRU5ErkJg\n" +
                        "gg==").bitmap,
                iconColor = 0xfff16033.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "游戏高能时刻",
                packageName = "com.xiaomi.migameservice",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAARNSURBVGiB3ZpPaB1VFMZ/JwRBYqQboS/FkEhAE6GIwUYXYoIbG/wDQhaVQhDERdoI\n" +
                        "glB3iRREBKloIkiwSxdxY0WDCPqiIGppjUJTW5EKYvuKGERfA27kczHzePPuezNz7/vb9oMwb+69\n" +
                        "55zvu3cm988Zo02QNADMAlPAEFBIXAFKwJXE9Ttgw8x228WhaUgalfScpHU1j/XYx2gvBIxLWmmB\n" +
                        "fBpWJI13Q8CwpNcklTsgooJyHGO4UyIWJJU6KMBFSdJCu0WsdVGAizUfjuYh4ixwf8u90Rq+N7PJ\n" +
                        "rAaZQiSpvXxag5ml8u1Lq5D0YWfoNI9gTpKO9fCdyMMxXxHzvWbqgXmXtzkihomWDnuDhrD7uApM\n" +
                        "mdlvlYJ+p8ECYSL+Bt4HzgCXgAeBR4DHPGw/Bb4EvgXuBO4Cnidan+Vhb8z15boaRcuOkBn7E6XM\n" +
                        "vpIOS9pKsduSdDjFrhD79UFZjZYzCls7/ShpX1aXSdoj6YRjd0LSnhy7fbF/H6y4xmMBIiRpNouM\n" +
                        "43tE0rKkkQCb2QAuY0nDxQDDZV9CrUDSkiefRahOiAcDYnzeftoN8YVnu4MAJmkQ+MfT6KKZ3ZMs\n" +
                        "kHQr8BQwAlwGzpnZlqc/KiNsZnUjLekCcLeHm9uRdCjgsVp1Aj0kacdpsytpKUBI6uMqadWT16E+\n" +
                        "IHNV6eAD534N+A94k6jnngX+AJYlPRPg1zdeGib78ZuAAP41s83KjaI55F7gHTN7MS7+WVIBeBV4\n" +
                        "lGiyrIGk6UbOk+WVOGa2KWkXGMjhNoSkoufwbbuB4/I6YnF5MY1wTpyi0/60B7diyIj86txfjK9L\n" +
                        "QHKkXop/XsjwNZP4XQReSfpwsA08kMNtqJ/quVMeaoSYWUnSJjAt6SvgJPAE8DRQBt5r5CT5eAIo\n" +
                        "3ru55Qmc9+BWcBeNWXBHBDObkfQWsAg8HBd/DbxuZmcCfGdhO79JtPotAYMebS81KjSzFySdBKaB\n" +
                        "HzJ6Ng0zOfU+I1IKedkPBBJsG3xe9j6iEfHBn50k2yJK/UQHyj7Y8fWqaKl+H3BH4m+HqDN2gPNm\n" +
                        "djmMayauIGnO89G6xUPAEaVvqFz8IukNH5YevuaQNOAZOPMsVtGeoxk0nDgTfgsePgYqjX3SAlMZ\n" +
                        "wfJm6zwczfA9mWO7DtX9yGdZvRLjyZRAE8C7HvZZeFvSXEpd3qK2yl1RwiYP11wPkiYknWttMGpQ\n" +
                        "J0bSRzk2o5A411K0kT+So/534Buijdh+YIL8lWkotokmwb+Ijpf2Z7RdNbOjUCtkHDgN3NZmYp3C\n" +
                        "NeCAmf0EiUPsuGA1zeo6xGpFBNxER6Y1aYW44ni3WTWB40kRqVBvU215aJiKS80A6fpIublITcHd\n" +
                        "/Km3hOGptjMKx6ksEd5Qb1Nxfqm2ADHz6v4HA3UptnaJufE/4XAE3dgf1TQQNKYot7LRAvmN2MdY\n" +
                        "fsR0tP6foCpqEHicaP/g8+HZWeBjMyu3I/7/yvcaMeUIKhcAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff4389f9.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "风筝守护",
                packageName = "com.kiteguard",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMwSURBVGiB7ZlLiI1hHMafP7JiSC7NxAZRbAZlksQKI03KZSMLC7KgLERsZqTUZGc2\n" +
                        "lB0LTbkmTBozGWSjIaGxYGpcIgkjC6mfxXfo853vPee7vXOk89ucc97L/32e733P996kOnW8YFkr\n" +
                        "Ag2SZkuaIumdpLdm9iNljImSmiQ1Svoi6bWZfc2qKU3DS4DjwHPi6QbaEsTZAfQ4YgwCR4Alvkwc\n" +
                        "BkYdjUe5A8yNiTETuJIwxihwuGgT3Qkbj7IiFGNNxhjdtTbxm0U5TBRjBujIKQBgBPhQQJz2rCY2\n" +
                        "FtB40Wx06R1XwcvuTE/AL05NsfMIsE7STW9y8rHBzG5EE109ssqzmDy0xiW6jGzyKCQvsUbKhhYw\n" +
                        "WZL/ZUI+GsxsNJwQ1yONYyQmD2Ua44w0jYGQvJRpjDPydAyE5GUomuB6/X6QNMO7nGx8NLMyba63\n" +
                        "1r/cK2W9IbmNXPMoJC+pjNz3KCQvd1OVBvpquz6Mxbmcd+7ZgTWS+lK5989SMxuMy3Cufs2sX9JR\n" +
                        "b5LS0+kykQjgYsZhcBXYAzQXaCYfwK2UJrbXWrMT4FRCE1210JfqgA44KKmzQpEeM1sfKt8iaZek\n" +
                        "+ZLGS5pQ5TNJmceSrphZRxrtcWY2V+iN9lC5ZSmHY1q2hnVV2rPHYmYXJK1wZD8Mfd+WNnZK/jpV\n" +
                        "qWiE4EyqHTgH/NmZmdkDSfMk/YxUCS+vl+dVWoXFiUoBK2O68xIwNVRmEjAUyj8TypsGfPU5tsJ6\n" +
                        "K83sXZL2xmT1S9ppZsOhstcV7KW/SWoxs2el9FZJayW55pIFyrGRM7PqL6vS03cxSGSiA06X8u4l\n" +
                        "EUEwZAvrkUoNVTsufUWwHgvXOVbKOwc49/4Eb7Q3RRqp2DVAj4Kh4eKzgmF2OVRnn6STkj5JGlBw\n" +
                        "CfQ+Um+/gguiXISHVtUxBnRKOlilWK+Czdij0u9Zks5nFRjDgKTb0cTUkyLBDVOtOEFwRVcMQNsY\n" +
                        "G3gBbCnMQMTMaoI/uW/OAnO8mAiZaQZ6PRkYAeLmLq+GDlHczP0EOABMz6on8z17yUyzpKWSFipY\n" +
                        "qk9LWPW7gmOdl5KGJd00s+i6rU6dOnX+Y34BelGWTLj6gEEAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff4bb89b.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "醒图",
                packageName = "com.xt.retouch",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAL9SURBVGiB7Zg/SBxBFMbfRhCvCyJE0RSBiGAhgnhG0opNQIgg2JxwmLSCjRARki42\n" +
                        "imAjQiClXGGwCCpRESQSkBQHBxYWAa3sgoJJo78UN4d7sjczO7vrErkPtrl57/uzO/eYXZEEAOxS\n" +
                        "G7tJaD5KglRE/jiuOaMexIC/jmvOqD8RA+pBXJFGkP/qP6Iz+2CeSD2IDvUgBlw5rjnj4T0R4D3Q\n" +
                        "FxNv4kGAPuBD0EIRuAIKQA7IRBAZ0BzjByLwZpS3gvJavFvQHSBYAj4CWQfBHk2QHge+rPJSCuDr\n" +
                        "9hdOaYRvgA3gDdBoKdyp4eu05GhUmhvKQy1M+Zt0b3R+nACLGP5LQLuGo93Q26c0Tiw97VYas5YN\n" +
                        "d7EF5IGGADPNmr7mgPoGxbXl6CUrwKxjcwWnlO9gr89YRlOf8dX1qt7TiB5mBdiLSOLHN2BCmQyE\n" +
                        "WptQtXFhzwOei8ioiLwWkRe6/RsC5yLyxGEtLH6IyBcRWff8vwKPpRxqVERexSQWN76KyLqIrHue\n" +
                        "99uqAxgBPgMXMW6DsLhQHkZ0Xj3d4p1QL+V2Cz6z7XPEL6lsGc/7npgK0AW8A45ivPNHirMrMeOG\n" +
                        "UC3AW2DTwfym6m1JxbwOwLlFgPO0fWoB7IR4Gjtp+w0EsOSwtZbS9l0FYNIhRAWTafsXERFg0GD0\n" +
                        "k7p0GEw7RAdwqTF4DDxV17Gm7hLoSDPIoeFOj/tqxw21h2mFWDUYWw7oWTb0rN53iGmDoZ9AU0Bf\n" +
                        "k1rTYfq+QowZjIDmgEf5IGrCWNIhBoFrg4l5C555A8c1SU0yyhOqaDBwEILvwMBVJIlJRvmjmA43\n" +
                        "QH8Ivn70n3kACnGHWDQIAsw58M5Z8C7GFcI0oQC2I/BvW/BHm2TYTahLoC2CRhv600EFbpOM8oQ6\n" +
                        "sxDIu4bwaeUtdM5wmWRAK7BvIF+LGsKnt2bQ2gdaowis1CCO/S2P2m+XK3EJzASQD8dCXq0zHKAz\n" +
                        "E7dIjtvvWwuxklfrLCiNCyCXlMgQUEqEvFqnBAyF6fkH9qu11MPcAhUAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米运动健康",
                packageName = "com.mi.health",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAM3SURBVGiB3ZoxbBxFFIa/OaUiJAUN2IUlS44gDZGwRNpQuAClQ64o3LhylA4p5dlK\n" +
                        "Q+HSqdJSWRQEIRepaImUSCBFxoImFHEiIQobEN1HsbvJ5bjd2303dxfnl1ar3Z15838z2tndeZvI\n" +
                        "JPU88BlwFVgEFgb2AMfA04H9j8BBSunvXB7CUpfVTXXfuPbLGMvzALis7k1gvk576uVZACypX6mn\n" +
                        "U4CodFq2sTQtiC31eIoAwzpWt3JD3J0hwLDutvGYWkA8BD6auDcm06OU0mpTgUYQ1bx+JlNKqdZv\n" +
                        "r+6C+u107MTV2ZN6a473xDjdaguxMW+nLbQx7DsNQSxRvDq812kIZ69nwNWU0u/VieF7ZIvXHwIK\n" +
                        "j688Y16MiMWrwQPg7Rmbiuov4OOU0iG8OiI3ODsQUHi9UR0kAHUF+DUY8B+gD/wM/FSeuwJ8CHwJ\n" +
                        "vFueew7s1pTbAd4Ktn8ppfQbAOrN4OzxRK194qoL6nflttBQbrWMFdHNwUAHgQCHwR6slXoY8HEA\n" +
                        "kNQLwEmg3Q9SSkeZQd4HfglUvdgDrgcq7uSGAChjbgeqXu8BjW+VNYr0Wlv9Eaiz2qNYIOiqR20L\n" +
                        "WnyT73eI/SzgZ/EcL1c5uqhLr613jP1vx/IAC9ER+XRcAfWaeq3uuEFXAn4WUU8CU97XYyC2G+o2\n" +
                        "3szqNwE/J7UfVmP0hbrWcP0x8EO5VaqOHzdArAGfhxypR4EeUL3fMr7a7pNZvR/0ctSjWL6MaE3t\n" +
                        "B+uOgugDTaPcpKfnKNZio9pWSSntNJT5ZFyQEiLyIKx0jLobHM5BhUdG7Wdofxd1PUMgDcDYPLt1\n" +
                        "0Trq+UzBtAOM+SC0SGm8eI3IpbEw5oXYHwy8mTGwNsCYF0J1czD4cubgOgLG/BA6nCByOomb/pQh\n" +
                        "9qr4s1gO+rPcv5M57ujloPLEncyNQQGQGwLgTgUBb+qSaXnh9qxdBXR7EKJWzjfVNk4jU3G1GSBf\n" +
                        "j5TbsGpTcG9+6m2g4r3sjrrrXhNEaznfVFy7VFsHmA1n/8PA/1JsuWDO/i8cQ0Bn+6eaEUArFrmV\n" +
                        "SFqi0kEZY2USL5PPBC+hLlCs7K/S7sezh8D3KaXTHO3/B+D2/g8/PXZgAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffe74a4c.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "王者营地",
                packageName = "com.tencent.gamehelper.smoba",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPnSURBVGiBzZpPiFVVHMe/Px2kUDGZEjXKUqmMmJqKolaWFlRKE6K4tM1EQS5FaNHY\n" +
                        "n1VQO23RokWLJoMJWxSEDjQIZRMmWhjZWJGNoYimaWnxaXHPme68ec93fvfd+977wOHAub/f+f2+\n" +
                        "73fvPecentRGgD38z8vAinbGLwVgiJmcB3YBSzqdXxLAYB0ReY4A6zqdZ1OA8SZCpm63TufaEODZ\n" +
                        "RBHdLQb4zCmk+8QA1xQQEXnQE2tWVSICy1vwfcJjXLWQW1vw/dZj3K1CdpvZbo9DT8FAqUQhj0ha\n" +
                        "G1qje/+KpDFJY2Y25A1khdJLBBiRNCCpz8wO58ZX1zHfb2ZXqsynMMBoeAP1Vh2r6mck8kfVAdol\n" +
                        "5PqqA5T+sAOzJS2VtDg3fIOkybJjlQqwENgKfA78AJyrs0pvCrZ3AVuAWzqd9xTAKuB14JfELcfz\n" +
                        "wW8zcCq8CIaAOzsp4k3gb/fuCV4K/q/VjH8AbGyngBXAgQIC8gyEuT6qc20MeKpqEZtbFBA5CFxH\n" +
                        "9qOcaGCzE7i5ChHbShIReSvMW+87PnIc2FKmiI0liwD4C7gdWELjqkR2dauIyKshxtWqEvmkW0UA\n" +
                        "TADzgXsT7bcXEZFS8jKIa8veRPsNjXJutNcaVLbNqJqB0H+caP8h9T8BZn6PkJ36jas9QiSpT9Jl\n" +
                        "SUcT7SckPW1mR/KD9SrSrmpE1pnZ95L2J9ovl/Ri7eA0IaEag63n5mJ96EcdPoPAffmB2ooMqL3V\n" +
                        "kKSHgJWS9jn9pv3gtULWtJRScfrNbFTSbw6faVWpFfJoKWn5uSf0406/qapMCQEel7SwQBJfS3pO\n" +
                        "0h1mZpI2Kf11GukPvVfIkzNGgDcKLGgNH1BgA3A6cZ7J4LO6QA731wb+yjlB07cM0E+2i03htuBz\n" +
                        "2ZnHVmn6M9KXVMyMbyQ908zIzA5K2pE4Z3xwU9eTyMNSEAKskjTH4fyemZ1NMTSzdyV9mWAahRx3\n" +
                        "5CHlhchXjbOShp3BPk2wKSrkJmBOESHDZvarM9j7CTZRyIRzbkmaG4Xc7XDyVkNmdlTSF03M5gML\n" +
                        "5K+IJM2LQpYlOpwIK3AR9iTY9KpFITcmOvxYIEjkZIJNr5lNSvrXOffcWWRntakr+jFngDwpQuJh\n" +
                        "9wXn3PN65Nvt/uQMkCepIqG/IGlBneuHld0Vp0JbJGmlpPEeSZ7/gfzssK0l5TQ+LyTPDkkjZnao\n" +
                        "kWOPmR0gOzDrl/R7aKclXQztUuj/DK0QZnYSaGYWb63zubEXzKz1s60yAd4BvrvKvmlnsBsBzhDO\n" +
                        "ibsS4FrgMeAVsmOgSzkhw8HmH+CBTufqAlhKdkD+NrA3jLnPDf4DjheH8k6I1TQAAAAASUVORK5C\n" +
                        "YII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米金融",
                packageName = "com.xiaomi.jr",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPCSURBVGiB3ZrPaxVXFMc/5xGXRd0YQXCbRUIadBP6w0WLQcGFG/0DSim4cCMoggst\n" +
                        "uGizqnTV+B/ETc1GiYgopdhFk5egYFaiIOa50KoglP74djGT1+lj7p0zM2/mNX5heI977znne+bO\n" +
                        "vfece68RgKQZ4AAwAUwDM8DeUPuGsAl0gXVgA1gxs65bWtJZSa/1/8NrSWfzOFuOE8vA4WovsDXc\n" +
                        "MrO5bMF/HJGkdvnUg5n1+Xe2/kj6bjR0qiPL2dKCE8DiyBjVw0kzu2aSpoFbwJ5RM6qIF8DhDjDL\n" +
                        "9nUCEu6zY8BUg0bWgZ+Av0nWowlgfwN2psaAyQYUA1wHvjCzl9lCSTuBT4FDwOcki25dTCKp19Di\n" +
                        "9XGRdUk7JJ2R9LimrR71+ebiUZnXKWlc0mIdg51iM5XwZ5nGZtYzs5PAtaoGm3JkUsnaVAqpM19X\n" +
                        "sjiUDymMgxU5XSpryKTG46t1YC39XQfWzKzncOYq8KXbyrC7wIlLDl7jKjGbNTVGinBR0p1Yg7TX\n" +
                        "vvcqbOPTihPIhOKDkLQDuI9j0RxVj/Qh6YdQnZn9QRLiFKINR56lTwhfSToVqf/ZZaXkIH2QPh6s\n" +
                        "SjqesXM8LQshd2ND0m6PMa8jPyoTO0k6UdB+KfLilgIyRyIyT4bhyKqkXTnKD0ZkjkZIHQ3InI/I\n" +
                        "LBeR9IyRFTP7bbDQzH4lWeAGsWlmN0LK0rrNnKoPIxw2ikh6HHkXqVvLKfvLoTOvjUcuCI8jE5G6\n" +
                        "vB7Zp8wgH0Raty+n6peKHPqKi/AkIjsXkFmNyIRmrs8iMkMZ7FKSnuYZGI/ILCkz6JUM8tCMJUm5\n" +
                        "GyCSdnoIeh05FnlbRSH38/SJIRhESjrmIeh1ZD5kKDV2x6knD9HgUdK8R4k3aOwCh8zsbcRgpeCz\n" +
                        "7aBxBrjgILTg1AewEHMixWm820UlP4PgzJLRd8qhJxYkbukplViVzUe6wKyZ/V5AYi9JL87w74q9\n" +
                        "lsp3zSxvZR/UsQj4NzBK9ogk3XYrrwhV2OOqmrNfbNCJ0jsodRyRpAUls8rInajriCTdlWMCcDgw\n" +
                        "LulqHSKoeNX14BtJH1RwYFib2M9N0g0gmJ2VQBe4R5JjL5vZqwD53cAc8BHwCcM5Vrhpkr4Fzg1B\n" +
                        "2SCekiREW0lRkwc982M4sq+K2J8+bZzZb3SAFeBNC8aawhtgpZPe7bg8ajY1cNnMuv2gTdvj6sYg\n" +
                        "+lc53r8rHJmKK60zKo8rjhSgv5PY1GlvHfQUONKLZWfTJLcipkjO4qdo/4bEC+AB8DD9vW9meVtQ\n" +
                        "/AOiEUtV2iHH4AAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffdd5333.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "番茄免费小说",
                packageName = "com.dragon.read",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAM6SURBVGiB7ZpNSBVRGIbfM0UZ2UZTC0Vq26qSiDLQTa3CaOFCiogWRe7EFkWLWhYt\n" +
                        "iiCSwEX2A+76oU0Z9kOurAxSW4RJ6SZByHRRBE+LmSvXaebeuXfO9aj0gDB6zved9z1nzty531H6\n" +
                        "jw9QDjwCpoBuoCVBTCNwGfgE9AMbFkNrPlHNRPMAOAFsDPq1BEanIvo2p9Vh0luRgF+S1hQZ/tsY\n" +
                        "szatBi9tgoBnjmLnsWVk0FHsPKmNADWSmlKk2BbkcANQBpwBxmM2eyGMB7nKSi16fej3VuCDBQNh\n" +
                        "PgCtobHW2TJxA9ga+lt/CUxk6A+NVQtcyaczdo8AVUHSdknVoeaXkgYkfZY0k3RScjAT5BoIcmdT\n" +
                        "KekM0FNwVvwPuS9Zs3QhT/8yoB7YBZxMMOsdQd968uwLoD17tYAtSU3sixh4EthcwER05jDRWUCe\n" +
                        "KuBzrlsvV/DjGAFPgNoCRHRF5OgqIH438CZGy8V8wadyzCT4T5WbwHFgBxDeO+F8fVmxfXn61gA7\n" +
                        "gaPBGD/zaIl+P8N/OozlCY5iFhgBeiNy1gN3A2H1Ee338d+AZ4sYN/oWA64XkSxMIbfOWQvjHQsn\n" +
                        "LQe+W0gMcDqBiUOWxnpeqsQZYr9c4b+K2KRZklYH+Q8kvSUS8hDolv/h9lrSH0l7JB2W1GZ5rHZJ\n" +
                        "L4wkAd8lVVkeYDGpXhUsTbtrJSmZ8yTtd63CAjWeJHdfauxR7Una5FqFBapX1IqsCCOGdDWppcKc\n" +
                        "J+mraxUWGPEkvXetwgLDnqQh1yosMOJJ+uRahQWGPUmjrlVYYMQzxoxKeutaSQqeGmPGM3WtW06l\n" +
                        "pKNHyjofAQYlNTiTUxzvjDEN0sJK43JclTuZiwUnVstsVb5J2m6MmZb+rf1eW3w9RXM+YyIS4JLl\n" +
                        "4kAp6Ehklfiy6VLgapTm2FNdYEzS1rh2RwwZY3ZENeQ8ngYmJCUuXJeYaWNMZVxjzsNQY0ydpHPW\n" +
                        "JRXOvVwmEgPsBT462A8TwBELE7HATDl+VX2xuA1UWDURMtSEX7mfLIH4cfzH/96SGYgx1YZ/kpWW\n" +
                        "XuBgGi22/qmmQlKjpDr5T7na0LUkTQY/E6HrV8aYHzZ0rAj+AnsRptwbFr9eAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffd81e06.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "同花顺",
                packageName = "com.hexin.plat.android",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATlSURBVGiB7ZltaFdVHMd/16AHk1q6Ef/sCV1aWoNa5pyBA2dlZc2FL6wQi5IofKEU\n" +
                        "kb5wPShmxMpaWRRlCTWHDnqh9OToVXuxZmVsQQ8qIo7QZQwiK/j04p7b/7eze+8557//XkT/L/z5\n" +
                        "33PO9/fwvefhnnOvSAUVVPC/QDTRAYAVItIsIheKSKeIfBZF0chExy0rgFrGYhh4F7i7nLGCewS4\n" +
                        "XUSmi0hBRM6Y3x/qOvn9KSKzRWR7jrsjIvKeiOyNouib0Fw0vIUABRHpFpH5Jcb6RETeEpGLRKRe\n" +
                        "RBpEpE61D4rI6yLyfhRFp03MJ0RkssRCD5UYdzSAtpRhEoJ5KT4LwANAF/CX4vYDu1X5sLmREyKk\n" +
                        "DVgJLARuAK4GrgBqgCnAJOCE4e7zjLEA2AoMptyIM+UScp9y6jWeFX9dQJx7LQHfqetjpSsoBpiq\n" +
                        "vXvwr1L0uZ4xrlE2PwLLTf1aVX9gPCLmAR9bd2qWw+Yew/vCM8a5wE+qF+ZY7UuBH0z7jlAB9UAn\n" +
                        "6VjlsN1qeBs9Y3UZ/s+2CMWpBfYZ3gZfAW9YiR8nnuC/mPKbDh8HDO8mj3gbDXc4S4TF327492cR\n" +
                        "6nIEFAynw9QPOoKNAH0eSenJ7RSh7FYCXwOL7IYWYMgSsAlr/QaaFKc6I0i1ad/iSKYR+DtUhLKf\n" +
                        "BmwDZujA36sE9wMzcxwcMbyWjPZW096U46NA/OArSYTlqzm50HcZoNZhmAyvFzPa2z2G3p5yiEgw\n" +
                        "yfz/KiKnVf0Mh12X+b85o32uxHurVBAvn60isjCKogGPPP0BPKx65CBQ5eAn24hzUtqOAssy7J5z\n" +
                        "DTvDWxqmYLRxtxLT7uC+YHiLU9pOApNT6pebyZ0rwnB7gdvCFBSNqxmN1hxuMq82WfWLgV0p/Cri\n" +
                        "VdFHxHrju6ckIcbJ70rIEFCfw/0K2G/VbQZWW3XnG3/OswwwCzilcsjdQWQ5WcNY9JL9vHgGGLbq\n" +
                        "eoHLVHk68DlwuWcO71jxDwIXhApJtszHgUXKWWcGv8G0z1Z1x9T1HDz3WoZvb+ETbAsRcZcybDN1\n" +
                        "813OiFevVaq8zvwXgMaA+DXAQIYQgCW+jj5SvVFQ9XXK2WMpdh3Aa74J58R/OUcE+BwHGD2Mnk1p\n" +
                        "14ekO622piQIcF2JIlocIhK0uRwlE2yEjO0JcKnhHLUTBvrNf+5hK8NvFfGE9kX68k08IRO84gg6\n" +
                        "zfA+Bc5S9R2hApRte4AIyHq2EG/VE/is8+cZ7kuqrgmYUoII3yFlY+wQI173E0wNSALgIVV2bTRt\n" +
                        "+9AhZaPJdqifopsDErkE+A1YYMpBPUL4kLLRYzvsswhvo57MjmTqgRMhAozdknGKSNCmnTYDPRZh\n" +
                        "mHhdv9EjqWXA84FCHimTkA/SnDcCW4BDFvlD4A5HYmuAawOErKU82OMKdCvwKsWzOcS99iBwdoaN\n" +
                        "c3uuuGnfTTSGVcw8rHZHk3/f/q0AdirnA8AGPOdRju+nMpLrp/jCvAfYkcHbW2rgGuIe6SY+5Z0i\n" +
                        "XnmuH4eY9cSfDE4C32KGihaiyn3E+0CIp0DmW56QBGYCjwNfGse7gFvG7bjoXz/briyXX1fQOuLz\n" +
                        "+xDxe+JSv2gl/vROA2B3uXINSSIR9SRwcQn2j2bMh50u2wn7PA3Uichh30/RxG9MGvIoURQ9XZbk\n" +
                        "Kqiggv8u/gGVZUO4tuzkNQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffb71c21.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "新浪财经",
                packageName = "cn.com.sina.finance",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAD8AAAAyCAYAAADrwQMBAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATuSURBVGiB1ZpdiFVVGIafNVYgmmIkpihORWSGP1GBZdSgpSVqQZr9KOWN0N9FF4WG\n" +
                        "kReRVDSFFpGWhhAU/Zg/F0FWRqmQhanVJKY5A2VQKZoRUvJ0sc/AaZpz9lr77DnSC4s1MN/3fu+7\n" +
                        "9t7rrLX2DjQB6hhgCjAXGAEMB44BO4BvgeUhhJPN0NIUqAPVB9Qd5uNHddbp1tww1CHqUnV/hOlq\n" +
                        "bDrd2huCepu6J9F0NaY3U29LGSTqaHU18CYwrgGqq8rQ0zSos9XvG7ja1WjqrX9GI8nqIuDlkrQA\n" +
                        "DCyRKxeFb3v1Uco13nQUuvJqO/BQyVoAvqpTcxAwEhhV1e8HOoGuEEJXH+j5j4jXSnq+e8PEHrXG\n" +
                        "qUuM+wX5TJ3dl8Y39aFxKzUmq4+p2wvSLI71ExKMvwfcXGDMmo0fQggXxARGTXjqk/w/jAOcr7bG\n" +
                        "BOaaV+8DljQsqbmYmB+SY15dCLxYipzmojHz6h3AmtLkNBdfxgT1OuGpVwAfAoPKVNREjAghHM4L\n" +
                        "qrXIeYLyjP8N7K20/cDxSjtW6YcCrcDoSn91g7W/jjEOvZhXlwGNbC1PAR8B7wEfhxA6UgnUNqAN\n" +
                        "mFRAy5bUet1FpzewRnlbvVsdWqh4bU1j1cXqtgQtc9X4TZI6SN2ZaPhntV29skzDdTQuUo9EautS\n" +
                        "n1evjSF+ONH4u+qFTfDcU+fl6geJWuvvPtXPE8iWNclrPb2rEwfgi1pEMyIJfldvTRA4Tr1XfV3t\n" +
                        "VI+qu9T16guV/w1uYACeShwAeyNZG5HXZTYLx4jqp66M1NOpLlcLnf2p7yT6X1Kd3KqeyEk4GCvO\n" +
                        "bHb+LlFQN2YUMN9WoM607uR5EcF3Rgp5vICQahxRrykwAKnP//buxKU5gSsiBdxfxG0vOKhOSDQ/\n" +
                        "O7HGX+pZqOtyAnP3xsbdPSlYm2h+QIEarS3ARXV43wohHMopPB54LkVsBKIm1m6EEP4AtibWGJ5r\n" +
                        "PoJkKtlb1zLRao/DzAgMS4w/2UK266qFXyJIkieoSCRdfeC8xPgjLcDuxKSeuKnB/Fo4EhuongsM\n" +
                        "SeVvAfYkJlUXbQP6F83Pwd6E2NTz+s0hhON55vOeu12JRWNxgjTzqSfLqwBQR5mtuXvD+jwWdXeR\n" +
                        "37IcbIt1oU5J5N7Uk6DWOvyoOiqneF+8vop6T6/2N/3NzqyeJJPqBK/MEbCgqMMaSNk1tidy31OL\n" +
                        "qN7u6IYcEc8UtvpvPJ1gfFki94P1yCaoB2okfqOOzRHzfgOmVdf1ofE3Ykgnq7/WIKg7AOpIdWMB\n" +
                        "051G3urqcPXVRP7NsYOKeqPZrqfWAMzJyZ8ZOQgHzSbaMyN1zVE7Eo2vjjZeVegWdW8d0lXq6ByO\n" +
                        "qepCsz3+K2aPxQr1dvXiBC1T1TWJplUfSTZeVXSo+lId8sNmx09jChepXfucysBtKWB6q2V9oaHO\n" +
                        "N7tFa+HPyiDNUxva3al3mb38OFbA9CHrzei9IOrLDHUYsKDSxueE7wY+ATqAQ1WtHzC4qo0CLgXG\n" +
                        "VvVF8SzQHkL4KSUp+rOUbqgLgPnAtNTckrEN2AhsCCHsK0KQbL4bZkvQmZWWdzeUhX3ABmBjCCF6\n" +
                        "/V8Lhc1XQ72ebBCuI/v2tl8ZvGSPy1ZgJ7AzhLCzJF6gJPPVUM8GLgHGAJeRbYsHAgMqffffp8gO\n" +
                        "LH6r9NWtA/g0hHCgbH3V+AdSpN9lnL/FZQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffe80000.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "雪球",
                packageName = "com.xueqiu.android",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASRSURBVGiB3ZpPaFxFHMe/k4oHk0ArgdgeUkO2aGKpYA6l5iIeQlIkiDSFgphWg9BS\n" +
                        "zz2UkooeihQPGpTQgxelNKIk0gaESC5FEGzQqC2xHkRooogKiaUBWz8e3mz3l8m+3Tdvs0nrFx47\n" +
                        "O/P78/3uvjdv3m+e0zoBaJS0X9JeSTskbTefkrQoacF8fiVpyjl3c7045AbQDgwD4+THuI/RvhkC\n" +
                        "OoHRGsinYRTo3AgBbcAZYLkOIopY9jna6iXiGLBYRwEhFoFj6y3i3AYKCHEuC0eXQcQVSU/V/GvU\n" +
                        "hlnnXHclg4pCANaXT21wzqXybUgbACbqQyc/ojkBJzbxmqiGE1lFDG020wwYCnm7QESbkqXDI1F/\n" +
                        "4WrckDQhaVbSvD9WJLX6o1fSAUlP1JDjV0l7nXO/lB0luRHlxQTwMtCShQkwAEzXkO9MWuBO8t+x\n" +
                        "p/znYJm4u3zsLSl5h3PmXKbccob8a6dB71+86w8CJ4HPgT+N3T/ALPARMBLk7s+ZezQUUcgZ6NEg\n" +
                        "zl8RvpeBQuCfBwWpdB/pL3u+VUbBOfez7XDObZP0bUb/HknXgSOm78kcPErcKZ3jWXHU+O4H3rSR\n" +
                        "gcnIeH3GN/aamSo6Nkc6XjRJtwF/+/63AzHvRMZtNb6xs1mzgEORTi+ahO8FY+8HYt6qEmvetM8b\n" +
                        "v4FITocEnI1wuAY84JO9lGLzQSDmjRS7ET++z/SdNn7XI3idbVBSIMiKC8652779bIrNYcyv65w7\n" +
                        "JWk6sDntnHvdt383/fa+MBnBa4eAmQjlh4uewNdVbO2v+4zpD+8htnAxZ/qfjuA1I1afp9XQYxKt\n" +
                        "VLHtNbbFa6WSCIDb+BUA0BHBa17AUoRDi0/SVcVu0JB9HrhDdRFF7PbjTRG8lmKFNPkknRVsrIg+\n" +
                        "4CbZRQA87m1aIngtxZ5aHT7JFpK1Uwgrohv4A7gFNJv+sQrxbxm7nghe8w1KypdZ0SpJzrk7kr4L\n" +
                        "xg465z72JHZK+kzSw5JWnHPLxu7fCvG/Me3HIngtNCipxWZFr2lfM+05I+IhSTMqTetbgVeN7buS\n" +
                        "fkiJf9W0Yyo3i7H/yAHT/tG095BMl12SLksK67hjxdPOOXdV0kGVF/OlJAFbJb0QwWtBJM8PMRgo\n" +
                        "epMsxWNgr6Eu4HszdsmMvRIdF2iMdJo2CfM8x6SJ2Wn6L0XGbCw6xm4LDJukRyJ9Ya2Y4+b70chY\n" +
                        "4zLOeZ6b+41/Xw7/cs/3HcCNyDjDNkB7DiIEJFqB85Eh7D/TBHySg8bqiYX8xYc9QZwR4AIwR7J2\n" +
                        "CjFGcrM87r+PkFxrX+TIvbr44AnUUg4aXhNQd1cAuz3x7WXGizPmbI6c5ctBPnAtBbppzNRcDV7E\n" +
                        "h5QelWOxqkBXj5LpT0oeij6V9Js/HpS0S1KHpDZJA5L21ZCjcslUurvFdq8j25Ycm7vVVg1lt+JS\n" +
                        "d4C4N7bcQqRuwf3/t96MY0w1o16YrCQiM9jcrbhsW20RYobY+BcG1myxrZeY+/8VjkDQ/f1STRlB\n" +
                        "BeA14rclLKZ8jEL1jOmofSYoiWqW9JykbmV78eyKpItBhSU3/gP4Pjxh5shJaAAAAABJRU5ErkJg\n" +
                        "gg==").bitmap,
                iconColor = 0xff3f7eff.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "企业微信",
                packageName = "com.tencent.wework",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADwAAAAyCAYAAAAA9rgCAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAARHSURBVGiB3ZptiBVVGMf/z9UgZC2SwqDQwt5YyiKjNqwUqQwsgmBjDZaWXjcy+iRE\n" +
                        "FG30paAghNo2ioICZaMX3MUgIZNFWiyp1ChyaUvYVgryg5EWwa8PMyPj7MzZc/bOzN3rD4Y7c+d5\n" +
                        "nvP855yZM+ecMZUE0CVpg6Q1kjokLY5/k+03SdPxluy/b2YTZeVQKcACoAcYBH5l7owBTwCXtFpT\n" +
                        "LsBZwJPAd02ILGI7cEerNUqSgPOBp4GJCoRm2Qrc3EqxG4GfaxCa5W1gZd1iX22B0DRHgN66xI63\n" +
                        "WGyaF8vQZA6xlFFAyXxgZvc2E6CR9yews5mgFdINDJUaEXi2pQ3Xj3vmqu+UJg30Sxps+qrVw/Vm\n" +
                        "9lWo00nBwAZJo6WmVC2fmdn6UKf0PdxTYjJ1cDvwaKiTSRLQKWm/pAVlZ1UxhyStNrM/fB2SGu5R\n" +
                        "+4mVpEsldYc4NIAzJW2sJp9auDXEuCHpNknzc2jmR7DgdhYrSYuBtb7GDUkrKkymLs7zNTwdaliS\n" +
                        "zvE1XKj2q+G/FHWheyVdIOlGBQg2mJejoiKelzRkZtNzDbBQ0kFJV5aWUnXcbWbbmw3SkHSghGSq\n" +
                        "5q2sWOAKYGlooIaiGp7P/GRmDycHwADwpaQfJB0hmj29xjdYOwgeyRxvltSVOl4p6QXfYO3QpE8O\n" +
                        "WYEHJS3KsbkT8BrtNcxsUtKekpKrmr8d59b4BEhGS00//SpiTNEaVMI+h63366WAy2uflXLzO/BK\n" +
                        "Qa6PAScK/LryfIpEv1SbnHy+IXoCrwPOyMlvABiO928C3gEmMzF2Ax2+gs8FfqxP3yn0OfLqBEZi\n" +
                        "u4Gc82uJLkbCuyG1fF/NQgFyJ+KA5cAmYCq2y3ZPWfv7gWOx7YwL43J8qkaxM7oSoBcYBo6n7KaI\n" +
                        "5t1my/0WYBfwHLAE6COq/Qec/sDrNYg9milzPfB1ge0m7xqLYi1i5rrY98DFLqfR6rQCsCNT3liB\n" +
                        "3XFgeaDgzQWxtrqclgH7KxCasCVVVqfDbjhEbByvaNVzOncxTZLM7LCkN0ILC+C/1P6EpH8K7JwP\n" +
                        "qyzA1ZJuKDh9sFBwzOGQwgK5MNkxs38lfZJjs8/M3guM65rFdF884KGy23GKo6SGdUAH0RdBCZPA\n" +
                        "ukCxAj7NKesEPl0V8EyFggE+zilzFalpV2AvsBNY5ZHvy5n440Td0rW+V+u1igXDLA8loD+2+xN4\n" +
                        "xGH3eCrmEJB7Hxd+8hAH+VCSa/F5VNKbikY1SyRdldqWSTo7tUnRZMMBSd9KGjcz1+gnncegpP74\n" +
                        "8HNJ2yRtM7NjKZtkMvI637h5Be0qqJUR4K45BZ1bHh3AnkwOvwBbgO5UrkELa3kFfdRKoZlc+hy3\n" +
                        "hfe782xNeoWkXklLJe0ws6A+sUyAiyRNFpw+ZGaX1ZlPLThuMfBcUJvtxWO+sbvg/ykz+8InQLsJ\n" +
                        "LurC8t7STg+A1UTjZYiGk0HfYf4PE9Jhxv7m5/gAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff3380f2.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "消息接收",
                packageName = "top.tdtt.news",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAP8SURBVGiBzZpNqBVlGMd/z+UWRoRhkWRiUvR1C+FKZgqBC1sY14jqJmEEUlwwpIW1\n" +
                        "iDYJSdBGokWLVi2MSjBB+iATJaNULE3TW1rgV18uFElSwsWvxRzzdJw5Zz7ec+0Hw4H3zPt/nv/7\n" +
                        "zsw788wENVEnA48Aw61tNnAC2Af8BLwbEfvq6k8I6iL1G7tzVn1dvely55uLurGHgU72q0P9ziuq\n" +
                        "7KzuBWbViHMAeCIixnvo3w4sBkbamj8DNkXE7hpxc4O8WHEmOtnVQ//ZHv3XpDAxVT3S0IjqkgL9\n" +
                        "VSX7v9/UyNIEJlQ/z9EeqqjxZF6OgyW93NNoJC6yUP0UONPaTgN3VdR4Hnivs7HUyd4ayYUVA/aT\n" +
                        "aRHxe3vDQMmO9/UhmSZM62woa+TvxIk05WxnQ1kjxxMn0oSTEfFDZ2NZI5tTZRERAVwP3AHMB6ou\n" +
                        "dF/UDq7OTXT5XZejPVJR4+baRloB1yYwMlKgXXZBXNrIRCvYkHqygYnlPfRXqb8W9D2jjnXrX/Wm\n" +
                        "cRS45PAowfqIeLyE/o3AGHALMAP4FhgH9kTEnhpxuwYbtfezSCenkiaREnVM/bKCmdX9zKfSoZWH\n" +
                        "2UPT3cB04C9gCzAKvJaz+50RcbBpzAlF/SVnVtZf7rwqoz5dcIiNFuw/pC5obbPUqROdcyHqzhwj\n" +
                        "4+qktn2eU492ObfWmZ2PUy6nkQUFyb2qvqye7mKgk/2mWBAbmPmwQrJlWJsXp/FVq4SRmcDhxLKn\n" +
                        "IuK69oayd7+1iYgjwBuJZaeoOxJrliPx4XWBf2+X+j4jLRNv9Ul61IKqSnLUGVa7OlVlO5SYEfUa\n" +
                        "s8to3QVqCTC5Zt8y3K+O5BpR56lvq3uAP4GtwB/qMbMidu4DUgEPpci2B4svaVGfKTmlG9XZ3dTV\n" +
                        "G1IfRwXs6gy8raLAcbWwcKfOSZ9zLscG2oKOAw9UnNLpwMfqwwX/X11Rry5TBwDMHnqq1mAvcCXw\n" +
                        "QrKU6nEq1GHga2BSr717sCwi3jF7WTO/tc0jXQG8GzsGgTk0NwHwitnCd1UCrapsHiTdiM1MpFOH\n" +
                        "DQP8/yrtVfkuInYPAHP7HOg08HMf9T+C7I3VTtKZOQgcav3uBbZExG/qIuCTRDHaOQx8AID6ZqJF\n" +
                        "aWu3iOqaRHEucN62xXgA2J9odLqW+yNiJWlnZVlEXHzdoQ6r5xKM0IIy0dUVCWK9VCS+uqHwhipD\n" +
                        "qT5l9xJQEZvUB3uJj9c0sU29toqRVrzJ6nLL1Y+Pqiu66f2niqJuJO/evpjdwGOtAkNtzE7a24Bb\n" +
                        "yV4pnAe+B34k+2TqUEScqypa5hOmw+pK9YomBlKSW9cy+6jsUeBesnuxOcBXwHayD8s2RcSJiUqy\n" +
                        "DP8AdqCiPDfVYp8AAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "腾讯会议",
                packageName = "com.tencent.wemeet.app",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAIESURBVGiB7ZgxaBRBGEa/XQLXiCQQRVCCkhRREMRCBRvBNDZpgtdZ2aVLqY1BK21s\n" +
                        "bLQQQVAwSLo0pkgjYqEW2VOLCFrYCLGx0UKehW6YXG7vZv6dcxOY120xj+/BsjArJRKJRCKRSGwB\n" +
                        "XI7gOAmciLHHOuAJfzHHAKeBD0DRSAzwiO0ExwBngA3HUQDHh7G3asADeuMdA5wHPvdwFMCxYe4v\n" +
                        "B9yriPCOAS4AX/s4CuDQMCPuDogYGAPMAN88HAUwOoyI254RlTHAJeB7gKMAWjEjbgVG7IgBZoEf\n" +
                        "BkcRK2LRGLEVA8wBP2s46sVEiCj5HcFhiyFeREzCYtidESV+MezuiJL+MeyNiJJ37vbcjZB0I+gd\n" +
                        "bJZTwKvyIZf2ZETJOWBZkjLgoqTVhgfVZTGXNNH0ighM55LGawg6km5GGLIk6XmN86Mjkj4ZD3ck\n" +
                        "tbMsew+MSLpu9CxlWdYGcknLkmYNjg1JEvAx8NO341oK3DF8Qp91OVrASqBjEzjrSn55Hqy8W+N/\n" +
                        "X4GuCMexD3gR4Gl3C1r/6kwRjmfQDbIywnGMAWsenitVgv3AF2uE47lvjXAcB4GXfTxXBwkOAB1r\n" +
                        "hON5aI1wHIeB1z08876CI8Aba4TjeWyNcBxHgbeOZyFUMFknwvE8tUY4jilgHbhmFTT3G7ML/ueP\n" +
                        "u0QikUgkEonENv4AQa58WnCHXkoAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff2f77ff.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "中国大学MOOC",
                packageName = "com.netease.edu.ucmooc",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAASnSURBVGiB7Zl9aFZlGMavO1wjNmNWpmLgaAoVBCvRGhaMIguKGhhYGiSOKal/iDFE\n" +
                        "ixr0BZLIgohqmRVUjKyobK2iYYTrAykzKvGjj5VmbhHUQEP59cdz3nF2POc557wfeyO84GXnPc99\n" +
                        "X/d97Tnv89zPfaQz+G/BKh0AaJTUKmlU0qCZ/VLpmGUFMBvo5nRsAi6odn6pAKYADwDDMSIK2Ae0\n" +
                        "VzvXRACrgySz4i3g2mrnPQbgDmBXDgFRPA5Mq6aAG4C3MyQ5CagDXvTY7QdWTLSAK4FtKQJ6gKkx\n" +
                        "vi3AVx6/HUBrpQXMAjYD/3gS2Q7MycC1Ejjl4dkCzCi3gHpgI3DEE/hDYH5O3jrgSQ/nIeCecolY\n" +
                        "AXzjCfY5sLDEGPOAjz0x3gOuK5Z8EbDTQ/49sLgUATExlwF/emJ2AxdlJWsFXveQHQZWllNAJH4d\n" +
                        "bvdPwo/A6jSSZz0EfwOdlRIQk8s83AqWhPdxddxpjr0ep4eAiheYccA9bkMeMWeHjVsSDLuBKdUQ\n" +
                        "EAb+x+3RsOGDkcHnY6etyiD+cTsO1BYM4mbkOLChyIBzcPvDENBfjn8K0Ay8kTArjWHDpN/IHqAt\n" +
                        "Y7B64H7cwhBGTwkCZgLPJOQG8Gmc01aPQy+eChVoB35N8H2tCAE1wCOefMBt1JclEbQBez3OGyL2\n" +
                        "twHfeux3AxfmFLEWGE0R0UVaLRYQ+XAAt3EOemyOAOuAmhwCluA2vDTcmpXw5wxkPjxGjoMS7lzz\n" +
                        "hYfvaOT7QBbS5ojTftzhKAteAS7NIaAZeNfDdxBYAzwRMzY5jbwn4tAV3J8LfOAJuoOMZTxuJXrB\n" +
                        "wzWCa2BMDuwXxNjcmxbkRMj4JNAUGuvyBC9gs4e7Bv/snsIdqGbF+B6M2B7wibgpYvxSZDyLEHDL\n" +
                        "8NKI7/oUn21Asye3h2N84o/EuHZOGDenCOkCvvQk1w8sx9/jepMMhybiq4/lhfGzIvZNke/J0xfA\n" +
                        "zK6QtFjS7zHDCyU9J+n8mLGdkm43szYz+ygtjqShmHtj+aYJydSnNbNeM5smaX0G8z2SOsys1cy2\n" +
                        "Z+EPYsTlkijk4tD1b2Y2mjVQEGyTpEskbY0Z/klSp6T5ZlZs7RUVMyZkUtKApMPFRDKzfZLacfXV\n" +
                        "QkkzJfVLesfMjhbDGcKQpPCZPVHICUnnBNezgQWS/pI0IumPPBHNrE9SX+5UAwDnSZouaUbwd3qQ\n" +
                        "Xxi1hYuokGOSGoLrcyV9UmwixQC3od4paYmkLIXmscJFVMiwpNQuYQjXAFeZ2Wc5fMYB965kmaRF\n" +
                        "kq7O6T6cRBo98mbFy8DcIkR0kO81RBRbfOSdwEDw2QX8wPiyxYenswgCrgf6ShAArq/QkBYrGrgW\n" +
                        "aMKdQbJgqYcry6yfxPXY1uA6ni1AI+HWTymIETLgSaajCBG7cc3y+rIknFNIG8n11tqMIr4mY4Oj\n" +
                        "YkKC+w248jsOG1NEPEXMC6GqCAmNtwHfeZIO4xBw14QKyCoksGkhfZUbAS4vZ27RorFkmNmgpLtT\n" +
                        "zG4xs73ljFt2IZJkZq9KWpUwfGMgtrpg/JLblWJ7X+SRWjdReaYCmAqsCn4v/paMxo7HA2miz+D/\n" +
                        "hn8B/6EvMiR/OQcAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff27af50.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "AcFun",
                packageName = "tv.acfundanmaku.video",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAdmSURBVGiB7Zl7kNZVGcc/593lsu5iNEkKGkqON0SJLogX1NBoskmGspjRQuyClplN\n" +
                        "RtbEdGHMyREdrRxzmkqszJpRK8scwxvaRVGEDFPSkLiIIqAsyy6w+3764zlv+9t332V3cWmaZr8z\n" +
                        "7+z7/s75nXOec57n+3yfszCIQQxiEIP4f4Rarx6wr+dJAz2gWge8ERgJTATqclMbsBJoBramlHYP\n" +
                        "5Lx7NEQtAaXcz7yo9pRSR6FPZYwRwCnATGAKMA5orBqyBVgHLAV+BTwANPdklDqkMGd7vw1Rm/Ki\n" +
                        "3gkcDQwHdgHP5cmfAHbk9xuAjwIXAscAQ/Y0YQFlYDnwXeBOYFse7wDgdOAkYAyxGa8CzwD3Ao+l\n" +
                        "lOx1dLWkvk39nLrO2nhQbVBPUh9W23vo1xeU1UfVyWqT+kwP/TapHyyss+9hoc6pMelydaz6BbXl\n" +
                        "dRhQjR3qperhNYwpq58orGumusBwvZoLn6neoZ6afx+hbiwM9hd1nHqzumsAjahgt/oj9TD174Xn\n" +
                        "D6n75zWdq27Lfa+vZcTc3MFsTEkdXRjwSfUQ9d5s1L5Ch7pQPU5dn59dktd4mPpsoW+rOrtoxDR1\n" +
                        "S6HDXWqdepD6tBEr71Cv2MdGVLBTvUCdZbjc9LzOcwxDi1itNpWyLQcTrFTBToJVhhMs9E1gPDCP\n" +
                        "fZB7amAocBWwBriDTiZsINJBEW8BZtSro4BbgRXAOQT1LU4pqY4laO9PwO/yBP8tjALmA1cSyRWC\n" +
                        "/l8t/IYw7H2oS9UP2RlMTWp9/n6++n71lj66xFZfHxVXo91wp8nqMHWousju7r0Mwx/bDGb4ohFk\n" +
                        "Kb90pnq6faPZFvVie84De4sH1fHqW/PmHqL+Qt1e6LOqntA+bwJOzZ+TgVmENPhbPtr9+uAKDwO3\n" +
                        "ENn9qL33qG6YQmi396iLUkqr1TnANCIM6oA1GCdRQVm9MFs+Qj3GnrN7EVvV96pTjBPc0UO/DQb/\n" +
                        "9xdXqJ8yqH+CIUy7oEQEcTn/XgvcU2ibROidPaFMkMVO4GdAPXAbITKrcRnwc7oyZF9wMqGcJwL3\n" +
                        "AVcbsTteHaMOQT0476jqTUb+2D93urGXnSqrj6nHqo/kZ3erR6r/rNF/ltqozlObrZ2TyoVPBS+q\n" +
                        "7zLkURFt6l/NeeRF4BqgA5gOfBY4j1ChE3vZqeeB2cCledcAzgDOAj6ZxyxCQiUvA2bkXa7Gy8Av\n" +
                        "6XqiBwHDgBdq9F9AlAeh+9XbjaxZVteoJxjUXL1by9WX1ZfUqepX7K67mtUZ6uyqtoUGpTer3zYk\n" +
                        "0DxD2Vbw6/xuNY3PUa8r/N6lLqhYUwLIhc35wA+B3cB2oAk4tMYO3Ehk3Y8QJ/ZVutcgjcDNQCvw\n" +
                        "buDpvMMTgPVAO6ES/gBsAk4k4mdFXlN19obI4Fvz9xbga4Ti6DQkG7OdcKvZwCNE8NbCscD3gOMJ\n" +
                        "aq6uAiFkzEjgx4SrfQC4PBu2Oi++lMe6iXClNqJA+zi1ZVAZ2AL8lnDdq4uVaheoh+ZjbTCC/SW7\n" +
                        "4zb1DDuVcm/YbbjjZ4yAbVB/2kO/DUZNMrOGa81X98trnKt2yW2lghFNwHeAI/JO1RH1dTXaCc1V\n" +
                        "0V1PAYuAV2ruTtDxROB64DfA/cDU3NYG3E1ouXpgNFFa16LuDuBsIjmeB3xLHV59EiX1SiP5Ha3e\n" +
                        "o55lJ6UWscTQPi3qZkPe1xmlcX911u0GHY8zyoWy+nX1S3an5unqrXmec42ke406tHgis4BLgD8C\n" +
                        "bwCOyzv0XI2dGUcEXZmQMKuyr95P+HBfIXBXSqmFoNX78rMtBPUX46RMENBoIoaXAk/mNX8DoGRI\n" +
                        "9YqeWgScmV+YAjxO92MuEzce7cRxV9o76J43ekMLQL4Vacljbad7sG/KbaMIkpkOXEcwYJ2aSoRv\n" +
                        "f5mg1KeAD+eBTsiGNFcNeiCRnLYS8mVEfj4G2L8fRiRgkqG0Gwk51AFszgsu4lmC4g/Mf+cCDwHT\n" +
                        "UkqXd7keygOebafg22Bc9yyp4dtHqk9kP16onmbteOoN69WPGUmxNc99inHJUcT3jeRaKXN3WrhV\n" +
                        "6Qb1J4WXy4bivMzugTdVvbOq70Bgm3EdtKzwrMMgnosKhpTVxy1QcH3BiCFETbwKGJvb6glF+2ng\n" +
                        "8ILNRxH1dNFNBgKvEQp8QuHZowQJtRNl7vOEyz9AQUX/x5CU0m61Eh+NeeFrU0qb1RuAawuDDwM2\n" +
                        "EgE4kJcRrxCKoEgw16aUXlMXEzKlrbLkYr8umialZEqpnFJqTiktTyltzk0/AH5f6Ho8sIH+s1Rv\n" +
                        "2Ai8nU7ttoRcH+V17ch/yymljpRSpY6qKc5qoRW4mE53ejOwmCio2np6qZ9YS4jWkcRurwMuoCLR\n" +
                        "BxLqRPUFdaWRkYepJ6o3qP+w/5l9Zw7s+YaGKqlXqf9Sp/a+ok7027/VScDngYtSSq35WR3B/eOB\n" +
                        "ycQFxFgixzQSJ99B7O52ImBXAn8myGVrJReopxFBv6JP/z7YW0PyZA0VI3poLxFKYRghLkuEIthF\n" +
                        "uGIrYE8LVet6lOiDGMQgBjGI/yX8GxoKNGogGi8MAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffc92330.toInt(),
                contributorName = "scrpr、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "今日校园",
                packageName = "com.wisedu.cpdaily",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAnqSURBVGiB1Zp5jF11Fcc/Z/atr6ULROgCCAgIlkoEwaBFQlACJiJIgKjEBcQFW0RA\n" +
                        "BIyiAlaFRowYiCFEBDEKREFBRQKUEhUQIkSQUoG2NC3TdtbXebN8/eOcX+/t7Z2ZN4MbJ7n5zfvd\n" +
                        "33L27Y7xPwJJjUAb0Ap0AJV4ZgKzgVnAnPjdDnTG/B7AJuAUMxtJ5zVNE4l2YEYg0AkMmtnaOvc2\n" +
                        "Ab8Ejoj9jYDF01AYLbZZ4ZjVeSKmRYikfYA7gYW5S/slfc7M7qzjiHbgIJyzE14FDAFVoDfGNwPN\n" +
                        "wPbi4ikRImk/nIi34kT0Al24SvxAUs3M7pnkmDZcCgAbgdtwVdkGbAW2AH2BbH8QMAwsAO4C5gMD\n" +
                        "U8G7SMRbJD2lDB6Q9G5JT+bmXpJ0/CTnLJD0cqy/W1JRbcbbd7CkDbHvtukScUiBiIckzY93h0t6\n" +
                        "JvdujaSlE5x1QA6hm6eAw2GSNse+G4rvG+o4YDEu/rfhevsIcA6wv6TTgBbgPGBNbNkXuFHSUeMc\n" +
                        "WcE9Fbhq1gstZKawZQr7dnDhb8GFMUmrJO0p6UhJgzF3r6Q2SSdIWp+TzPOS3l5y5lJJvbHma1PA\n" +
                        "ZWncKUmXFN+PK5FA4lbcsMeA1cBpZrYBWIt7FAP2B+aY2X3AMtxgifnbJR1UOLoL5y64UY93f6Ok\n" +
                        "Zkmtcne/V27fLEkH5NeXei1JS4Cf4G4yEXF6EIGZbZK0FlgCLIpnPfALPIitiLP3BG6TdJKZrYvj\n" +
                        "U+wAmC/pRDzozcBj0qzc04W72xZcZdO+i3FVP3FcQiQdDfw4kKsCjwNnmNn6wtI7gGdxNzkYBI5J\n" +
                        "ugn4eyDVj0utmts3g0wTvhDPVEDASJy9A8ok0gJchKtIFXgR2BpuMh9pV8TYBHRIWohzcW6MFWCe\n" +
                        "mRU9U2eOkLFATDkkR4EaHld6gW48zhyCa8gIcDmeHYxPiJk9CCDpQOCTeABrw8WfcqGkBp3xrp1M\n" +
                        "7AlGgW+UMGpGjMM4wx7FuduHM247UDOzWn6TpBVBSA14zMz+MSEhOXgHcAG75jn1wgvAtSXzlRir\n" +
                        "wMNm9nid53XGOMwUU5RW3DNtwzk2EJfnn0FgM+7XR4HzgTfFvqvMrKfk3CSRWjz1Qn7flAi5A/hd\n" +
                        "IDuC6/NojDv9bWaSdD4wL/Y+Ctw+zrlpzUg89UJHjEOU5FrjEmJmvdQZeSUdjMeQJlzXrzGzoXGW\n" +
                        "z4qxhqtJvZCXSLX4ctIUZTKQ1xcXAHvH1D3AAxNsyRNSl2pJasWLKnAp7sKAaRVWBTgOOBV3CpuB\n" +
                        "68yslNNBdGduam9Js3HPlyrCDtwLduABMXnL/XKETMW2JgdJFUmPRP4zKmmlvIQdb32HpLWxfljS\n" +
                        "JklbIocaUX3wlKTO4tmvVyKfwktW8PzrejMbnWB9K55ypLvnkjmO5FaT6gyRBccq7jlrwBOU2Mi0\n" +
                        "CZFXi5/JIXBjMUiVwAjuzRpwl91DFsET8smt98bvHVWimankTGD6wS7V7kcGQtuBv5jZ4HTPq/PO\n" +
                        "RsJBFe1w2oS8DkSKXRLD87su3MXOJkuD2uOpALvhDYuZwHNmdmn+7H+H10LSbrj7rQBPm9nWkjXH\n" +
                        "AMsDoUog2kWWy7VRP2PvmiqCcyR9R9Ktkt41wbozwwuNSboy5hrDS82O31+K9/XCmKRqeLV1kl6M\n" +
                        "OyQvFXaCySQylywDvqOA/NnAoThnF8S0AcslnYXHgVagRdK+ZIFwFOfoGrxU6MNTjmTYPWSOoB+3\n" +
                        "PwEHA/fi6rVtUkIkNQdi7cDhgdAYsFhSG64WN+FV2oElxHcC++R+pwbbzCC0H/hyjAtx+2iPsQ0v\n" +
                        "refhtpLaqD8FXiJz3bs0H8okshj4WVyc/L6Ay+L9euAWvOB5IpDsC2JrOJeTS+0FNphZVVKSyBDu\n" +
                        "ai8EPoEbfvJGDYFTMXX6I97MS4TsklWXEdKJi68YPauB6D/xeHBKEHtgXLw23n0Q7wY2A7fkUvmu\n" +
                        "GFPQ2yvmhuPckXiX4kgKgFXgqcCnJRj2Wj2EvIAngdtw1booOHAasA4YMLNRSd3At4Az4sKz8Br+\n" +
                        "UOCLuC204moErj4EgsPA1cAP43eK4omoGq4FjTGOBeOa4+/5khaa2csTEbIZuD/+TlKp4aJdHO9f\n" +
                        "AU4CTsdt6HbgD2Y2ImklnkgeBpwn6Tdm9hBZPdEXCM/BE8HkilPZnOLJvCC+OQiaSxZ/roz1X5+I\n" +
                        "kCXAzXjBf3fMDQEfAL4J3C9pNfBxvDGxDbg21R9m9oqkZcBSXAWSJFI9MYCryHeDMdOBBtxGd0AZ\n" +
                        "IfvizbV0IbjRpjxnDOfS1WStmefSZnm3pYK7S3BvA25P4PrfgOv+RlzaQ2SfDvaI+4eAG3FmbAJO\n" +
                        "Bt6PS/QrwM8nI2QRLsoeMt/fw66e5Dq8QTEIfJSsPXMA8H3ctT4JrJIXRsnYBwO5j+F2sT09ZjYg\n" +
                        "aTnwPeBV4BIzGwgG7R+E9ONqvCmPTFmFOD/GjXg6QWzO96L68dJ2G6GrkhZJmgl8O5jRByyPdKVC\n" +
                        "ZiM9eCb7PN6rugF39+eENBfGuq1xV4LEiFowYycoI2RujK+RlZfdubUCMLPVeLtnFA9iX8W79CfH\n" +
                        "3HVh5AQhaf9ANCv2A1biTmN3vM/cQMa8FJvKCKmri5IIeRX/1AUeSVPll68JVgLvA44Gzo45Ax7D\n" +
                        "JZOgQpYQ9kvqwlVzEW4bF0Y/uZnMlpqAY2LtvTlCSkvdnSQSKcju8XMDmbH35IjeQUh0Wpbh0ksp\n" +
                        "eTdwQdLtgI4cIT3AucAJcdaPzCy5eyNz+UcBv8XjUKrd6yOE7HMwuI62xd8DFFQrB3/FjTrBM8DT\n" +
                        "hTX5FH0BHnCb8I9GKwr4JEJGcWmtib3J8fThHm0nKKrWXNzvp+orEVIl091iTX4mzr0ERwAfwV1n\n" +
                        "grxEPo27727gcjPbnFtXw7OCBlzKPbhhN5F95eovfpouIyR9hBnM/Rbupcoy5cV4lO3CudeIc/QK\n" +
                        "SavM7NlY2pkjJKnrXcCYpPfiDKvEOe2BdCceRGfgmpK8aVkbdhfkZsUh3bguphS+H3fHT+BqpDDC\n" +
                        "q+KCYTzqt+Mt/72AayR92MyqZCl8Hk4FPoRLp4ks652sSuwumywSsjAO3oLrYcpO+4Hr8TqkJ9zn\n" +
                        "RcDxcfF9eDxoAY4F3oMb87m4d5pTgmAlzh4mK6xSGzX1hdP71G2pAb+vhxABq4A/43bRittENbxQ\n" +
                        "irLHAZ+P/euBK8KDIekyPEebDVwq6eEgjkDuetxBVINhg2TRPWW+iZD0u1ZmF+OCJIvoiqQlkv4k\n" +
                        "6UFJhxTWnSvpYkmflXSsch/9JTVJulzeeRyT9EKuVv91qOR/D4KoaTW5Jc2W9GihmbBR/hXsjQWS\n" +
                        "3impL4gYkXSe6vx3jf8rkNQg6YpQq1/JO/FvTJDUGSq253/6rn8BYPRdwj2JTVQAAAAASUVORK5C\n" +
                        "YII=").bitmap,
                iconColor = 0xffdc4748.toInt(),
                contributorName = "scrpr、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "中国银行",
                packageName = "com.chinamworld.bocmbci",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAc6SURBVGiB1ZpdiF1XFcf/+9wzmWkV09DaJjP5mEmbJmkKpRaalLRQsVptxSIRFEV9\n" +
                        "0GJFQStaEcGH6kMxKGqpiMXig1ixFaoxWLC1UhBDYlo0ZJqYmJimaYrWfM2duR/n7PXz4dxJ7pyz\n" +
                        "z51779wZ9A8HLnvvtfb673P32mutfZwGACBSo3GthodvldmtiqKNQqMSV8m5IUmRpIak/whOy7nD\n" +
                        "kvZJ2ivpiHPOL9QGt0ACmyXtkPRBSZskDSszej69SDJJTeCIc+7Xkp5yzh1YiD09A9jmvX8SOAN4\n" +
                        "Fg4PnMXsaeD2pSAwjvdPAOcAGwCBPAw4773/GXDdYhCI0zT9OGb/WATjy3CCNP0UMDQYEufOrQB2\n" +
                        "YjbdxeRNMzuB2R7gbL7TzKYw+zNwHLNGF/pmvPePMsXbF/omxoCngbTDZClwzHv/Y+C+er1+HbDK\n" +
                        "zPYFxk4Cq4H1wL3AD4EjQFKq3TAz+y0w3h+JmZkxM9vdgYABh7z3D1GvrweitgVYDvwlIPMKcGXb\n" +
                        "OAes9d5/EbODdHAcZvZcrVbrjQxwBdmbKEMV7x8F1pbId0UkJzPmvf82cKHDvLugy78ZEAM7S1bH\n" +
                        "gFfTNN1B2xsYBJGWnCNN32dmxwh7RcP7H9GNAwA+AlRLSBwENnShoy8is6jVahPAyyEyZlZL0/TT\n" +
                        "8xmwDrPjQRJmk8CaeVdiAEQkaXp6ehVmQTLAKeD6cgO8fzwgBPBaN29ikERaesaBYyGDvPdPBoWa\n" +
                        "zeZWAr4fqJKm93U7+SCJSFKSJO8m5ADMppIkuXN2XDz7o1KpfEHS8rwiM/tpVKns6mD0kKRrJbV7\n" +
                        "k8uBy5wrxI7DkrYCU21tZzQ5ecRt2dIM6Y/j+DmZPaYoekhZQJrBubfEcfwlSX+8ZEy9fj1ZAJjH\n" +
                        "UUpcbIvEhyw7xc8AU21PlfAh6lt9l8aanQX2kaYfpcQTUq2utOycyeN8s9m8+dJA779G0d16vP9q\n" +
                        "GQnv/YPA+YDyflH13n+jw6J9juLimPf+kdkBDrP9AcXHqdfXh5QmSXIbWQQ8aEyRJHeVEBkDDucF\n" +
                        "zOwQUIkkjcu5zQHZ5zU8fDykNI7iz0h6W9nq9Q/eqkrlgVCPc+6UmT0baB+XmpsiSdskLcv1p/J+\n" +
                        "l3OO4HSObSpmgSap3uNjObMk524roxlF0W8EjVzzkCzeHku6Re3eIMNpVSqTZQqdc3nikvSs0vR7\n" +
                        "ytLYi0glxXEspenc0XEswQNybkdOzzDgQotYr9ePjoyMnJTUnnQ5RdE7YsENKvrJk5JeLyOSN7aF\n" +
                        "/W5o6PcdZIpK4CZlOf98uiVJIyMjb0g6oTwRdEOEtKogYfaqc266F6MkVXocL7WdY93AOdeQdLTY\n" +
                        "w2gk6ap8s0lv9GHU0sDsX4U255ZHKm50RVH076WwqS9E0blCGwxHLhBH/N/BORdRdGeS1FNgt8Qo\n" +
                        "xIOS6pGkNwvNZkUH8D8CM7sm0Hw+UmhjR9Fa4PIe5+inftuTDLAsiqJC2AScjp1zk5Lu1tyTeo2k\n" +
                        "UQVdnaRwbffmVpxUeg4ELNuq4hbttGdXAhO5bY1z7mAs7/erUjHNPQdWSdqsciKh3OEeKpW7ysKa\n" +
                        "EgznG4BmFEVlOjYoW+Q5IjJ7OWqk6Z6AYUOSPgAEV8fM9qq48pFzbkTSZT08c0MjJCftKSEhSe93\n" +
                        "zuXJJ4qiP2XyWZKfxzFKqntJkmxnsLnILKokyXtCcwKjwKGAzCFeeCGLEPB8nUBi5b3/StnSeO+/\n" +
                        "TOdiWq+YBh4umw/vP0uxtGrMJlYtthstSznz+DuwulQ5fBjYS1a06C/VzWRfStP0E0AwXgOuBg4E\n" +
                        "9F2gPdVtrfAvKNaQDPg+nauKy2g0tgC3A3e0nruDOXZWQbynbdwdwI1AYdO36XfAtyj+Ywyz3SGB\n" +
                        "bYTT1yng3rKJSiYfWDkIuBOz0H6cAt5ZJvSTgADASXq4QRoUEWAdWSWnADP7ZSfBceCfYTk7AIwt\n" +
                        "FRGq1WvI7lhCJdPXG43GpvmM+BiZBykjE6ysDJJIrVZbB5SRqAH3z78SEOP9dyi/VjhOdtu0KNcK\n" +
                        "SZK8y8yOhEiYGcDjdHuvCKwAfoWVXtxeAHYyE3bN/RABVgLfpMNB27pBu7orEhcVz8ysxux3ZUoB\n" +
                        "b2aT3vsHW3+Fi+FMt0QAx8zMGu/9583sb3S+q/wDMNETibaJ1gDP0PmjgITsQvMxsjNiolqtriQ7\n" +
                        "KPOYBMZqtdo48F7v/Q/Iqofll6HZX2x33yQukrnAlcB3CTuA/IwNMztmZi9i9mah3+ycmb1I5lLr\n" +
                        "8+kDaq2rtlAy1debidM0/SQlly6LATN7DbifQX0wkCM0ATxBtiEX6xOOC8DPgY0DJ5BHkiTbzewp\n" +
                        "soBvEB/VGFl49Ez7LdSSodFo3Ag8jNlfyQ6qlO7elLXG1oEDeP9Is9m8aSG2DKSmRRZ+b5C0VdIt\n" +
                        "gi1ybhRY0coaI2BG0nlJpxy84uGlSqWyR9Jh51zaQX1X+C/UcmKzpBKEUwAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffb62934.toInt(),
                contributorName = "scrpr、fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "Jump",
                packageName = "com.vgjump.jump",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAUDSURBVGiB1ZpriFVVGIbfz0uRmtKUVk5ZImoqkanlJUOLoAtdzKxMoqKSNKOIqIik\n" +
                        "+mH5IwoJycgiSDTGICEwLamEHG+ZTmqmkl1MJ0vzxjg6mT792HuPaz73mfHMOfucfP/MrPdd37e/\n" +
                        "d6+z1zp7rWMqEoD+koZLGijpMkmVks6R1DHuUi9pn6Q/JG2RVCNppZl9X6waWg2gL/AK8B2tx0Zg\n" +
                        "OnBlOQxcDXxUQPG58CkwuhQGzgc+yMCAx3ygZ1YmxgP7S2AiQQMwqdgmZpbQgEdVsUwsLqOJBCuB\n" +
                        "s5qr01owUS1pRFHuSOHYJGmwmR1JE9vkigKW6P9jQpL6S6rOJaYaAd6WdENWFRWAQcDHp9QTGFPO\n" +
                        "h+EU8Ziv25yJTpL2S2pblPuXLSrNrDZp+I/WbJ0eJiRpbthoHBGgr6TNJS+nMIw0s2qp6Yi8XqZi\n" +
                        "CsEbyT8mScBFkn4vWzmF4QozW5+MyKNlLaUwTJZOjMgWSX0C8VdJe4N2d0kXlKqyPLHHzLoa0EvS\n" +
                        "T04cZmarkgawWNKNJS0vPwxpJ+m6FMEbuyb+u1DSOkWTRIOk8ZL6xVqdpLfi/48rGsWHgxxVktpL\n" +
                        "GpujmDclPSSpwvHbJE1TtL41xLnbSuomaaikSZIeETDLrZq/hVmAEYHWzmk/Btocp90WJg34NEyM\n" +
                        "tQUp2ss5jCf57gDmCfjKBc53HZ+K+RrHj3Jxtzp9WaBNC/gZLm5moD2dYuSWQO9J9H4/zF1rnIDN\n" +
                        "LnCK61QV89MdPy2IOQC0DbRzgeOxVue0LsDRWPvc5RyQYqRryk0FCBfz6QL+dIGXuOS7Y/5ex38T\n" +
                        "xLzntAmB9rgcgPeBA56PtdogdoPTlsb8hwHXA9gn4GAQuNoFXhVovQO+0pm/3sUlOyy1SgHRiPXI\n" +
                        "oc0J8s4K+LOBncC7rn8NgDdyk+v0TszvcvzEIGaH09oA9bE2JuA7ApemFN6faAlI2g8GuSe4+M5B\n" +
                        "+zxgUdyvTsCOuLHcXaA9cDjWtjvt7uBi45w2OeY3Ov5JomepfcB1Bo4ATwRc9yD3hSnGK4Bngb1B\n" +
                        "v7pwOuzmAl4ItKMED12s3w+MdVy3uLAjQD+nbYtzzQm4dTH3muu7A/jXcbcDC4FDnIy/k+n3PhfU\n" +
                        "IRiNBLPUDIimxi1xXz+6D7hcw4BXg7Z/NlcDXzuultzYKOCkL4zk3gKagXtIgV7AVKJRSFAPLCda\n" +
                        "FLtw8szocYhoArgH+DbmRgfXqGghfkGy+bY2CHq+haBjRDPFKpqu7Lmwt+UuHKfpLmY90Il4raDp\n" +
                        "5JKGlwy4XNJ6Sc9J2iBpUXMfoRKhQdH70RmSUPTN+8xm+o9KHG+XdHHm5WWDg5IqkhereeWspEAs\n" +
                        "MLNjyYj0UXSKdDpihJmtaCNJZrZV0tLy1tMq/GBmK6SmuyjPlKmYQtBYc6MRM1sr6bOylNM6rDGz\n" +
                        "xtcAv2XaVdJfJS+pdehtZo2v5E22TM1st06PraEXQxNSjoMe4BNJd5akpPyxzMyu9WTOEytgjaTB\n" +
                        "mZaUP36WNCDt1CrniZWiLaBNmZWUP3ZKGpr30ZuZNUgaIml1rj4lxFZJg8xsT0FZyOZXDqeKJUCH\n" +
                        "4tyPyMwUTmzxlApTi2bAmelL9HuRrPElMCgTE87QzcAXGRioBu7K3ECKoeFEb5i/FFB8LTAbKOg4\n" +
                        "vNlfPuRpaqSiKXugorOWSkn+IT0saZeiWahG0nJJ1Wb2T6HX/w/46hXjH66RIAAAAABJRU5ErkJg\n" +
                        "gg==").bitmap,
                iconColor = 0xffff3a48.toInt(),
                contributorName = "sddpljx、naicfeng"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "虎扑",
                packageName = "com.hupu.games",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\n" +
                        "bWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\n" +
                        "bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\n" +
                        "eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2\n" +
                        "MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\n" +
                        "dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\n" +
                        "dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\n" +
                        "IiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS\n" +
                        "ZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD\n" +
                        "cmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJ\n" +
                        "RD0ieG1wLmlpZDoxN0EzNjZDMzhGMzMxMUVDODJGNURGQUU0RDI3NjAyQSIgeG1wTU06RG9jdW1l\n" +
                        "bnRJRD0ieG1wLmRpZDoxN0EzNjZDNDhGMzMxMUVDODJGNURGQUU0RDI3NjAyQSI+IDx4bXBNTTpE\n" +
                        "ZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjE3QTM2NkMxOEYzMzExRUM4MkY1\n" +
                        "REZBRTREMjc2MDJBIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjE3QTM2NkMyOEYzMzExRUM4\n" +
                        "MkY1REZBRTREMjc2MDJBIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBt\n" +
                        "ZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+3d04vAAABm5JREFUeNrsmmlsFVUUx1/bV1ppKdAFqGWz\n" +
                        "lQpiEUSjxC2uuIT4gWr84BrXaoJEoggfUAyi4BI1iGvEGI3RiEti+NCglgjaYNksLigtOyK2lNpa\n" +
                        "LC08/yf5T3I8ubO81zYR0pP80jczd2buufds907TEolE7GSQ9NhJIv2K/N8knsI9aeAOcA7IB+Vg\n" +
                        "JXjmRFNEZvEqcBEYDPLADyfijIhMA6PU8QAwEYwGJUTkLbCvh30UC8gAx4BviE1LIfzKgzeAKerc\n" +
                        "UdAFsvlST2TmVkd4ppjoOJADvgIXgkq+owgcoTlv7U1nT3OcG8BOZJjzQyM+83FQCz7j8UwwG+wC\n" +
                        "e8BUUNDbUSuexH25Edt9DPYq05GZ7QRPgZqAAeyRj8QdI18P3uPoHQA7QQdoYQeyGRROBY2g1dz/\n" +
                        "DWgCQ1SnsxhE5G8bzbdXFemgzWpZA96krQ+kGZxNk5DwPJI+UMSwvUndOwhMYpudPJfHjn9A33gY\n" +
                        "fBvYK3H2CKSDseB8MAM0Jv4rf4Jt4EgiWLpBmXruSFCvrm8E2aCOx6PADrAeFAf1Ma58pRCU8u8I\n" +
                        "cBoYDopJIZ3XZfeFJEwOm9k8ymT6Ec//xvMrGaGawRXgAfpMLCz8iik8BB6hucVjfSP7aW4HeCw+\n" +
                        "MVb5hSi6wxH5yjjIuTSxX/xMKwvclegbOaZ+i5kMUyZRadpWO8xmhWkzJ8i0ZNrWMcoM7cGItzCC\n" +
                        "bQMbGW1eVGbXyQztSY65f2+EFNESFrV288WpKPIJmM9nSIbvVnnkWdWujdc9KXJERCsDrRGFJcQO\n" +
                        "xniXHGfIfBXcD3411+to9xJy54HnOUiDzAycwmd5UhyiSJz3JF00egVet7n2BqhSx1XmPonzc1QZ\n" +
                        "0Q4W0QwGK7Nab2bEzv5fjv5lp6LIQmbTxY7IMZvJ7FwwwRF+tfzDe0SRBVRsA6NNp0mGWmxRmMHM\n" +
                        "HmparuRS0UvR6poIibba3LMYLGSkymEiXm/a3B4UtbRM7mGu+JuR63BAm0LOarkZ6Xnq+Gn64oBU\n" +
                        "F1YlKXT+ACPXd2ALFdFSykBQAc4kFSHLg2IqEsm0XIoURCgas0wF/C6Yy9/DGNkkAlaDJ8HdjggV\n" +
                        "JlM4MLbSbouqSL7j3BaWBqvpwO9wWavNUTp8NUc+m9VwNWcjGSV+53LgZwYDm0fGRHH2OFhlnKsd\n" +
                        "lKs2JWBfBGd/ie0fDWhzXP3uBA+CUvWuMeCwuecgmG6dPd2xoisz5w4ynOoZy4owsqNUxetJF8P7\n" +
                        "Ui7EbA552yTlLMeMSDXwObgpqI7Jd0xdE9FOmRZBES+3bKIp3sOdlkvpT1+b53Q7/FNqsUzHs0XB\n" +
                        "D8G9fj4y3DHaex2lw3GfMqaBSa+eoybyPbjT0X6iOe5i4gyqs6y8TkVfiUcIvbb+GqJGeysztnR2\n" +
                        "M2uuTtUuLWAvygaV3WxbxQF9IuLML5OFoFWk3GcxZF8o4XQt2K6KwizuY8mK7nL61gzjXzFTQFqf\n" +
                        "qmEEbPBRRPzoS0ZHvQSYZKPWMkdUmelTXgwCl4GloBa0mnt3gSEB5cmnAdGsCQwHF5jzEtmmglnq\n" +
                        "3BKQaWdkrCP57OJvWcdfwu1SKR7PCymxuzhqrlJlOp/hJwW0jv2OjUA5/zK4kZl/rnV2aTTe3NjO\n" +
                        "5BTjmv6xJBJbrmMFeDO4DVzpE410ydNg9pd1RRyj+Xa5wu8ItXbwRJT4I8XiMYd+I3/vY1CQfapr\n" +
                        "A5SQgvM5LhX2c9aDZtxZopQ7tnp+NC/xk3XssK6cM7krM4EdCxOpq2Yx8sXU1mnkbx2eGUx23NgQ\n" +
                        "EGVamKGv47eS5Y6kdWtEJbx1f51jVpP6PiLF2emO6z8FbEjXqorXL0kmI9k+5pnUjIwjWo6FKNIc\n" +
                        "km/8ZDtzgZVWn1IkKUXGm7Lce+EeEwyCNgoORniflPYXg9cc15p9vrskZVq3OEyrXmXlONcVdh/X\n" +
                        "bjh0+6xxZAZeAKt4PC3ijCTtIzXMGRO4vR9n/ZRQS9XiEFOSTehDXCF6IglrCXhf1WDpjkGJ+ZQy\n" +
                        "rjwyNEiRBWoE5Pvd9eAL1a7CkWMaHVVAOxWRsn8FlWh2VLRnRNga9Uxxs+pbnjp27saHiSy2bmAH\n" +
                        "yjg7lVyO6vXHahaV82P+Hy4zmBTL6AOjWSkv4p5xShL188E+ZmV56VlcfB0ybbpZEa8NUMKLhmuY\n" +
                        "M9I5g4URg0Xo95ETXvr/qaZfkT6SfwUYANhmtgp85NB2AAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffc50000.toInt(),
                contributorName = "sddpljx、naicfeng"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "IT之家",
                packageName = "com.ruanmei.ithome",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\n" +
                        "bWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\n" +
                        "bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\n" +
                        "eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2\n" +
                        "MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\n" +
                        "dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\n" +
                        "dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\n" +
                        "IiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS\n" +
                        "ZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD\n" +
                        "cmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJ\n" +
                        "RD0ieG1wLmlpZDoyOTQ3NEIyQjhGMzMxMUVDOUFEMEQzQzEzOTVBNkY0NSIgeG1wTU06RG9jdW1l\n" +
                        "bnRJRD0ieG1wLmRpZDoyOTQ3NEIyQzhGMzMxMUVDOUFEMEQzQzEzOTVBNkY0NSI+IDx4bXBNTTpE\n" +
                        "ZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjI5NDc0QjI5OEYzMzExRUM5QUQw\n" +
                        "RDNDMTM5NUE2RjQ1IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjI5NDc0QjJBOEYzMzExRUM5\n" +
                        "QUQwRDNDMTM5NUE2RjQ1Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBt\n" +
                        "ZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+ttsLNQAAAkFJREFUeNrsmj1Iw0AUx5PaWqUiKloHQbu4\n" +
                        "uAiiCOIgilKKODmok5ODk5Pg5KibSEXFWUcXV8Wls5uTi19YRcFvqra18f/oCSHks01sr96DHyTN\n" +
                        "3eX+ybu7dy+VFUWRKsF8UoWYECKECCH/RIhfdTzHCIFsmfdbZn1/BnGwRyd9YASscPoy+kGjjAXx\n" +
                        "AAeDdMKxZyVpjHRwLoKsjlzrzUGFBDgD1arfKMZJgy4woCl/xMqHiuhkBjSBGKgxKJOS4FoJkFOs\n" +
                        "LQNiFJsZENWpM2ZS3gkt4NKkb7dOpt8qiyer554Bl1wnpPGCotYRmYkxsoCHY8DP7u/agqgUeK1Y\n" +
                        "s2ybl5VdsRLjtZC0S+08gg+7IYoXNgM6QQM7D4I7sGVSZwL0gk92/gXCoLmUQmYZarsC2yauMg2m\n" +
                        "eIh+7y38/ZWXMN7qnrLYWAkhQogQIoQIIUKIECKE8CuEItacyfVvl/qU81pI1iLEfrDZjpXggsJ4\n" +
                        "JxsrypLMS/n0alDzpmg312OznTawyOr5dAQOFSKEcr8nDjpRrvZETyQJXjgXkiIha2CTcyFxmX1n\n" +
                        "J59fYBv/Wsm7Dz00I1G2MsLuYzYhnNOTNhjHv1nPd7AL1mXNHwao8XrJRkKsCCHUuVGwA1oNyt2A\n" +
                        "cXAq6eeUZdUMl08buZQtL4QIODbIrlPmvd1Je6Vc2S/AMFjVuRZ0uDSURYiyBCZpCtWsTVzGWvug\n" +
                        "GxxqxgCXQeM1iIINNgmEvQpR/sJoVluW8ln8lNMQRexHysl+BBgAdOCwmTp9yf8AAAAASUVORK5C\n" +
                        "YII=").bitmap,
                iconColor = 0xffd22222.toInt(),
                contributorName = "sddpljx、naicfeng"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "邮箱大师",
                packageName = "com.netease.mail",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\n" +
                        "bWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\n" +
                        "bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\n" +
                        "eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2\n" +
                        "MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\n" +
                        "dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\n" +
                        "dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\n" +
                        "IiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS\n" +
                        "ZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD\n" +
                        "cmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJ\n" +
                        "RD0ieG1wLmlpZDo2Qjk1MzRGRDhGOEUxMUVDOTZEM0FDQjI4OTNENjZDOSIgeG1wTU06RG9jdW1l\n" +
                        "bnRJRD0ieG1wLmRpZDo2Qjk1MzRGRThGOEUxMUVDOTZEM0FDQjI4OTNENjZDOSI+IDx4bXBNTTpE\n" +
                        "ZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjZCOTUzNEZCOEY4RTExRUM5NkQz\n" +
                        "QUNCMjg5M0Q2NkM5IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjZCOTUzNEZDOEY4RTExRUM5\n" +
                        "NkQzQUNCMjg5M0Q2NkM5Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBt\n" +
                        "ZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+WYn/OwAAA8FJREFUeNrsmkloFEEUhnuSSTIk0SgaNB4U\n" +
                        "N9DxEONCFFQQJaLgQUVcQERFBTcEFTyIggguB9ccvKgX9SRevAgibiiiYg6SxJ2okRgT4hpjkkm3\n" +
                        "f+FrUhav2urpNpOJ8+AjM7V09+t6Ve+vmkQcx7H6gmVZfcTCdqQcDEhXRyKgFOwDl0B+KhyJBui7\n" +
                        "H0wHg8BYUAgS4Gu6ObIATFbKWlM174Lc9KMmzDKrlk+bR1EROLTCDO+EYduBoAzMBbvAOzAqqCNt\n" +
                        "TJntY7KLZfooqASPpfJxYAWYCHIkZ8VqOASMkdq2m4zIFDDYI+6HM2W5YAn4oemTB0ro2vPp80mp\n" +
                        "fjs4CGKGL8P2cmQZ2ARmJTFK+ZRL/FgH/V0Hjil1F8E9+jwU7NEmM0VrbQMnmHbfwVtmRAqZN/QC\n" +
                        "dDHXKKEYV20ExXo9GEZl4qEOgL1SuwJ6DtlqwITfPeAIUerw9hCUSe1crjBtP4Mcpq3guOb6/cAk\n" +
                        "pew9iCn940zfardeDq01zNv6AlbSWzZZuiM0DzqZumxNVPx0Vx7JPlF5UnlkGlPfoHHCK/ll+Wzv\n" +
                        "5WRSjhQx9V09lEvsoApBdsTpTZIjbEcyO8QeskhfccROhSNhzzchg5rCUL+tPkRjQQgr4Bvl+31w\n" +
                        "w9QR+S3OoL25a3GmvRB7O6RkJtTrVHCGkmsyJqRQBUkZWXnvBs+6l6puCVDNSIAqkAXOOclbHSgC\n" +
                        "hzX1QtIs1UiPtUp5B7gLFtNzRTmJwlkLmAMWgttKOAkpXqy0T9BwJ2g5F31OUTwX+hiFb3SosQFU\n" +
                        "gUZS1jMpEo6AszQi5SahJfYjr8B4eWKRXQaLGJVcobmWnzyVQywHddLusIU2ZO7ZWbHpHBEC8LWm\n" +
                        "LqbJS/1DOBISb/8Dsw3Q7lL/tvx2Wakx+3/L7BlHMo5kHDF8/p50JOh+J+FxlBTIkVxN3on4XFJt\n" +
                        "Q2Wcy6iO+jDUbzNTJk7o2zXtuYTWSLkqavD2Vyvf74Cbpo50eNQJ+bBKKevUtJ1Nek21q5LMV2V/\n" +
                        "XHpGcZ+dShuh4Z6YOiKOKTd7CLs2RiOpp4kjSfzFGF12SApJ2UaDao/n2gKuywUmjlT+g4lfC9aD\n" +
                        "p5qFwLb+PAh3SLReI9X7QL1glJlMNt2omTZIdogOCDF5C5ynEdXZc1eeK2HbpusgOyIe+jS4AB5Z\n" +
                        "Po8sQzbfP6rKjmy0uo/w0y5Ryx1S6YTDrFpWsiOSSsuTQkpM8pp0dSSbImIreOmRVPXHPb3kv4NE\n" +
                        "3miw9L89po36bQriRG8akczGyrVfAgwAdEkarANjcnYAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xffec1a1c.toInt(),
                contributorName = "naicfeng"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "阿里云盘",
                packageName = "com.alicloud.databox",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\n" +
                        "bWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\n" +
                        "bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\n" +
                        "eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2\n" +
                        "MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\n" +
                        "dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\n" +
                        "dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\n" +
                        "IiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS\n" +
                        "ZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD\n" +
                        "cmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJ\n" +
                        "RD0ieG1wLmlpZDo5QzAzMzJCRThGNDMxMUVDOURGNkVDNDYwMjkyRDNFNyIgeG1wTU06RG9jdW1l\n" +
                        "bnRJRD0ieG1wLmRpZDo5QzAzMzJCRjhGNDMxMUVDOURGNkVDNDYwMjkyRDNFNyI+IDx4bXBNTTpE\n" +
                        "ZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjlDMDMzMkJDOEY0MzExRUM5REY2\n" +
                        "RUM0NjAyOTJEM0U3IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjlDMDMzMkJEOEY0MzExRUM5\n" +
                        "REY2RUM0NjAyOTJEM0U3Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBt\n" +
                        "ZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+QmReQwAAA3dJREFUeNrsmk1IlEEYx101rUTMinC3JPow\n" +
                        "V4pKSgvStIhILaE6dAr20KXoC4q6BdWlSx2i6CJUiFqHqKDswwILDEoPFmn2ZamQtH1hBZulbv+B\n" +
                        "R5Btd+Z5553ZRHzgd5AZ39n/zut/5nkePeFwOGEsRGLCGIlxIaMtkg08YyooBMXAD7LBpBHjP8E7\n" +
                        "0AqaQQv4MRqF5IO1YAWYD3wR40PAC9KJfvDItBCPpmtNATvANrDc4e8OggegGtSRMPchhDikErSF\n" +
                        "zUQTKNL4DP/gZHImOBW2E3vcCuG+WtPBbbDMovFcAgHdV40jRIh4CPLi4KJ3QRkZhPFzpDZOIkSs\n" +
                        "Bydt7MhRcITrG2SrT0AHCIFUMI9eyTUO7L4C1JtyrS0O/ljPgjyQInmeHxwDIcbzuoHXhGslMy22\n" +
                        "F5Q7dJhC0M54dp0JIZuZZ8AsTbvMADcYayx1IyQRXDe99THWaVKsc86NkAWMb2qDidMYLAa/JOt0\n" +
                        "gDTOs6LZb4HCH+7R4WginoEayXgu1/ojhUym26wsrhg+O6oU43N1hMwAOZL532hHTIbIUdok44t0\n" +
                        "hWRL5neDN4aFDIB2ybhfV0imZH7Q0tXkk2Rsmo4QcaWYIJnfa0nIH8lYko4Qj+puZklIlmSsT0dI\n" +
                        "SPHteC0JyZCMdekI+Qg+K3ITG3GAbtm3wPcoOYpjIUFyplgxmyolpkO41nFQTgdgYIQl+3R35JVk\n" +
                        "vnC0UsvJ1QdwETTSz2coNxkWms7NR7Yq7llXDd2zZOSCrzHW7wS7OXetZjqkYsVGsNDyrhyWnGdz\n" +
                        "QAlnR0T6W6PYlZsWd6MADCrWL+EmVhWMq/xeCyJEqtyiWPc5mMgVkkS/oIpdhoXUMtbc77TSuJ1Z\n" +
                        "eDhkQIDINi8z1uqiNNlxyfQ0U0wV8GmK2AR6mOsEdGu/qeAtc5EgOEGpMqf4UMx8lYbjmtvabxG4\n" +
                        "D1KY1ilqtw1UqOuk28IANX+yKAMt5SZMFI/BOmoaueqPiCvDeRfnwpCLNt8LsIqyU9c9xAtgn+Kg\n" +
                        "tNGr7AGVKhE6jZ7V5BzxiAYntTMdl8kBjRYF9IODNjtWkew02IIbjnqwxHbrLRppJKjVxYf/AqpB\n" +
                        "WTxab5wQ/Y+V1G/Pl+Thv8FL8JRs9Q54/b/a06qYSfm9l/LxRKoFCPd5T9WYPpML2hIS9xj/p5px\n" +
                        "IZbirwADAFpUZLC/Lta4AAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff637ffd.toInt(),
                contributorName = "HeMing505、naicfeng"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "百度贴吧",
                packageName = "com.baidu.tieba",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAKMSURBVGiB7ZoxaxRBGIafdxEs9A9EBBXEIgcWNsHuAiGdmM5CQbERLAWLNCZpJH/A\n" +
                        "xkIEDViaQgQNeCIoWEkwKfUKGy0sNCmsXotswrHuZvZ2Nzd3Zx44bmfm22++9252dvabFRls3wQu\n" +
                        "ApPAeeB41iYSW8A6sAl8kPSot1G9BdvPgcuDi60Wq5Lmdgt7Qmw7Tjz1kCSABMD2YtxwqmN7AUC2\n" +
                        "rwJPI8dTl2sJMBM7igaYSYBW7CgaoCXbW8Cx2JHUZFujOltlORKhzw7wNj0+BdxoxKvDfLV9uob/\n" +
                        "2xl/7T77L0VSIpaupG5VIewsKQ6cGEMry3QJmzchg76FZIdGHpI6Zf2VsbXdAfbtt8o/0gYWAjYK\n" +
                        "tDdO3aG1lCmHBFYlONnUEiJpb7GZDrm+hTR1Hysza40Eh0KGjUMhw8bYCBnkEmWJnZVvltAS5Q5w\n" +
                        "KeR8kEI6ecuR0BIlvT8FhYzN0DoUUpd9npEeVPE3jP/IZJWThlFIJf4rIScy5Yn0+1Om/kfB+RMF\n" +
                        "9Y1S5j5yruCZITv/f9s9aOoZox+qDq3vwL3eCkm/gFu1I6pIVSGzkn5nKyU9BF7XC6kaZYX8Ab4A\n" +
                        "74BpSetFhpJmgXngI/CzdoQlKXONdCWdKWq0fRd4IWkvESdpGVhO29vk56Uek59UKJ1KygYSojA5\n" +
                        "ZvtKavPZ9oVAH8F8WMG5i02lTPOcT9l+AjxLq1rAe9v3bZ+t4rMuZYZWO/01T6afOWAqx+4oO9fG\n" +
                        "vO2XwCugCxQOyyYZ5P5I73ZCP1ynRIJubDZ6EmA7dhANsJ0woP2LA2YzATZiR9EAGwmwFjuKBlhL\n" +
                        "JK3w7/bAKLEoaWW8XqrpqViNFlH/rO6KgJwtMo/oi2d/AZEjomStBmkLAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff5584f9.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "皮皮虾",
                packageName = "com.sup.android.superb",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPrSURBVGiB1ZpbiFVVGMd/3+HYmF0sFJpKYSjpYQoRgsyHYIJ6yJJAGEhQ6qUbkUQI\n" +
                        "YQQzviTomxA09WC3KSgheqgpIhJ7qB66PByloDBqKhWVMqzGkn8Pax1cbvc58+29z2mf+cFi7bO+\n" +
                        "vdb6/nvd9llrGw4kLQGWAcuBK4AmsKhg3AT+KRhOmtlXHh9tHgFjwEbgcU9hfeI4cADYD3xkZofc\n" +
                        "OSWNSzqswWSyiIhB592s342MiEngTXfT1cd6SXtzLZK21fyUy/BA2/9GFHEzsPv/eZg9ZWP7ot21\n" +
                        "HqrJkapsUJhZacTWWKhCAO6H0CIra3akKqshCFnW54p2AHcDWwmLWpa3gNuBp4CfS5QfGkLS032c\n" +
                        "VabatUlapQsX2VcS+2SFeoYawEUlnoKXb5LrPcBIxv5iFDEEPFmhnhWN+e+pxHEAScPAXTn2ozFe\n" +
                        "B1xWoZ6VzQqZU/4AvojhGmBTTG87enWHfL/2qP5mVSHTwAwwY2Yn24mS1gNLgcUx6VSH/EsJD+Gn\n" +
                        "in40y3atf4FHzGyzmU2nIiI/xng5gJl9D/yQU866eexeSgn5ErjUzKa63PNJjK9M0vbl3DeaXL9c\n" +
                        "wpc2zaLT3u+eUnXur8AHktYk6VM5Zd6X2F9L0mcL+DVeVMid3kek89eMj2M4FX+34u/U6Vbye1LS\n" +
                        "9gJ+bSoipFtX6iQmz5m9kkaifU+O/dEkfyvHnseWIrPWC0WFmNlOSa8SBvW1wH4z+zqxb5X0fLSf\n" +
                        "jfZ00O8DbnRU5R4js0VF9ApnizzonbVqE+JkkVeIa7bKojB75b2atHvCaJ6tBO6Vfa5kBbsIC91M\n" +
                        "mhgFTACrgM0ly05xL4h/l6zgN2BMySZBZDzGf5UsN4u7RcoKeQNYA0wobLseAu4Fnoj2t7tl7tQt\n" +
                        "c+hv1zKzXZKuJ+wJPJcxbzOz9+YpwrsA971FMLOHJR0A7gAuJ4yZ983sQ0f2W53VnO27EAAzmya8\n" +
                        "8ruRdBvx7djBXL8HexVeKnDvGa+QrscPvUbSZ8B1BbK4hVxcwp/CSBqV1ALWFsw65x0jfRUiaTWw\n" +
                        "JYarShRxxitkcSeDwt7rLcB3wBHgT+B0Ep+Ot14Sw5Ikvgm4B9hQwvkUd4sMZRMk3QA8Q3iKdeNu\n" +
                        "kfOESHqMIGK45y6Vo1jXkrQW2E54zRgk5prAt44bhxSOurIvf4PCCZO0guobZHUz3DCzWeDTuj2p\n" +
                        "gpkdbS+IC+EktxM74dwZ4uvAL/X5Uol3IAoxs2PAs7W6U44dZvb5BanOraFBofunHFoYn3C4v0cZ\n" +
                        "U9iAHjROyCsiI2h3zFw3LUkTkjqdfPn+MCmcAY4koePbcI85CBz0fKP1H3RuHrYDyFBQAAAAAElF\n" +
                        "TkSuQmCC").bitmap,
                iconColor = 0xffb24c75.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "智联招聘",
                packageName = "com.zhaopin.social",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADUAAAAyCAYAAAD845PIAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAARJSURBVGiBzVrRVRsxEJzNyz9OBSEV4FSAqQB3EFJBSAU4FQAduAPTgU0FsSvAVBC7\n" +
                        "gsnH3YFONyvJvvNL5j0ecNJpd6Xd2dXahv8UJM8BfI4eb8xs9y/0GQQkX9jFaEgBU5ILkkuSf4Sw\n" +
                        "UjwVyhuLd7el+n4sEDABsChdMIN1veYYwHU0Ngn+PhfvnpFcJtZe1b8fLacFyRdHyDG4AjAGcD/Q\n" +
                        "eh2YmX1ITaiDdSiDAGCHExoEYAMASaMwrEE/0XaxU2ALZIwys5WZGaodjvEK4Es9/gmVa22cpb6a\n" +
                        "2QMq1zsl1kWzSI4cJrsVc1cp1iL5WzEiA6omOVPCCnWaAHn3A/zdVbtyKZ6FVKzWeogSqpoTe4Cn\n" +
                        "U979Mgu0jKpJRWFVj5duTlxFAO2NAZzYNLMtUJCnoI16FeWKZ9Q2M76IvEvJu4hylFrrufmjxCi1\n" +
                        "gMruHrM1J+GdVAkjlqSWN51K3E/FyUo8k0LNbJ0aHxBvblySfLOo2Ssue4B2gPc16jUzXkznU4c6\n" +
                        "X1gVt82PV+TOg7U87EiuE+PrZnNrKp8788oqeDo54wDMAmU6EPJUnuvEIsltNKdFWrmY6lsBNLGX\n" +
                        "zT31TnfiN4jJEDFRtebkjFI54xCkSCJOCZIFqeP6zJED4PiT8mq8EPsglynFLkhek7ysFVdEAwB3\n" +
                        "4T8kb4RexSShbp+sF43nKqyC8adk5OWxJHnH6vat0Nq0VPJVp7Q3s3n4IF4wQJjL+rrxBH6S3jTl\n" +
                        "UYOU+yllVdJNFpeZOUNgFj9IGaV25lij9gk5ffBoZp1mTsr9lMuogPQq5nADpgBuUFZV7Bw58ZyV\n" +
                        "Q/c+nIDsZG3qauIwYQNDuh/rG2SM+LpRk4QqT4p7dKeA534yTkiOIsNy142mUvgGbXxvmNmv+Jln\n" +
                        "lOf7L5FreSQRxtMyMW8IFBvlKTFC2aVuC7xd4U9pkKxsPEq/6CFoHyTDU/f5OjkKEEbVMdDH/0P3\n" +
                        "PEkcoTqhK5WjAO1+qSI2JIkR9ImGRs3RTdhTAD/Ee9+RZ81tXBIVgfpi2OnQkrx1clmn4I3eU3lt\n" +
                        "0BSgYkoxn0qmuZZYBzVxKJccNFkr9+tVyEblUdE7AC5J3kP37D08Z2S9w3GpqZinPr5MuhH9pskx\n" +
                        "kMwHRO5HvzWslC1109w7x8I9pTimcg1JAH5tiLxRQ1K866qxUeqknsUzT7mcUUUfZJcgde2IiUIZ\n" +
                        "FXdugIJC1lFkBmCWOGmFBbqbmG/8kDxn1djwOq1N4+OO5H0ieJcHKlyim8K85MW+ndgQyeR7oEHn\n" +
                        "jgyX+YB39xuykm4xJStGVS5cAk/5ZH5qjFIf1xyLmJWWGJb19sjE7gf6V/JjsBGsNHSl/pT70pXV\n" +
                        "RuXiYIR3F1Xf7gKqKrsjkNVNuc/9LMQewPioSj0Fkg8icF0fH5CEtvQrnhZKPvONoRZ2fdzMZqxq\n" +
                        "wj4l0qq4eAWQ/cJVDHavD7uDm4onxl9vndYJQrKswQAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "京东金融",
                packageName = "com.jd.jrapp",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAANESURBVGiB7ZdbiFZVGIafL4yETmRRiVpDJ3LKoaBiOlBGGBgSRtC5IAQvJaOLiGAm\n" +
                        "qKsiK6gwKMmMQIT0IsVILKIIDIxmoCBniIwxo+giiByIp4tZf212+/TvbP4u9gsb/v1+37vW9661\n" +
                        "9lr/gg4dOnTo0KFDhw7/FaJfgToO3NQgdRaYAY4AeyLi45L2VgJjOfpHYKL3RMR0v3XWQh23Hb5W\n" +
                        "n1BPzhtpoN2uXvt/MdLDhPpQn0Z6eF5dWlTXCcfVZTNcDrzp3BLtFxuB3er5+cAgjPQwpt7fQrcC\n" +
                        "2KaekSUXHJ+aWmMb8BRwc0HsNOCs9FwMXAOMpNgNwFZ1bUT80arnim9kPK333nO7+oz6bs26/y4/\n" +
                        "upm+rkjtLk7vS9UN6tGkfayViTojFZrVNWY2VGgvUnerk+r6xF2qvqV+1subl28kIvZERNWZdV+F\n" +
                        "9lBE3AbsADarnwM3RsSDwH711lZFtZmRjPbpillZ3kC/NZO/KXGvwzzvWhHxJLCvJLyqQRM7Mr8f\n" +
                        "Ud+JiHXq4kFsvzMl/KIG2r3AVOb9HnUNMDsII0f/hfYy4Msctz4ifh6EkVNK+GMNtMP8cyBWwGBO\n" +
                        "9pES/kiVSF3CnJEfcqFzIZ3sqkXimi2zb6gXUm6k7NvpYZS5q8EFOf5bmP8ZeZbipXUM+LBMpJ4E\n" +
                        "vAK8D5yXC0/A30YOlDRQdDasKenvm7JCUlurgTtKwjsjYrZC/gFgRHwKLMvF9mY7ebnioLo75Qyp\n" +
                        "GyvyRksMXKdurtCp3lKgO1O9U/0p5exSl6i/Z3ST6iJIV131YeCNihGZTiNxYkVO0dK4ivJdqoft\n" +
                        "wNk57hwgf9KPAmuBxzPcAxHx9l9v6kJ1X82oVeEFdX8L3SfqqgZ5Y+qw+muG21I4LOryFoWoHkr6\n" +
                        "NkaGrL/q7krtv5rh3qucY3Vdi2KWtTAyqQ4nXZWRu1LOpgzX7Iqs3qv+1qCYr9ShjK6pkefUhRld\n" +
                        "3shB9TX16hTfkvhJ+70aq5eoO9VfCgqZUl8s0FQZOejc0ijanbK3ylMz3EvqtPqR+qhpdypD5cmt\n" +
                        "LgCuBK4HDgNfRMRUSe7KAvow8H1E1P6PUkeA03PamZrzpUOHDh06dOjQocM84E8+V+euCt2HfgAA\n" +
                        "AABJRU5ErkJggg==").bitmap,
                iconColor = 0xffbf9964.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "慢慢买",
                packageName = "com.manmanbuy.bijia",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAR9SURBVGiB7ZlPaF1FFMZ/J/+aaKK1NjWJ1bRSW61FFE3FUGsrUYJbC1HcKFIU3JS6\n" +
                        "ERdSFwqCCwVBFAVF6qoiFYuFuLALI9iFRVqVlLbRaLGxxhjbpGmSfi5mHr3vZu5798Z7i5b3wcC7\n" +
                        "55yZe76ZN2fOnAs11FBDDf8nWBZjSXcCW4F6LzoNHDSzwxGbemAtsBm4Kic/o7gA7DezI4seQVKv\n" +
                        "pB9UjnFJ7RGbTZJOqjiclPRw3LeGBIfXA11RmZl9kcCvCegF9vrnh4DOgN0QcLb6dJWhB1iaxjBI\n" +
                        "BHgCGIjJuhNsDWiJPHcl2H0NvJHGKY8eYB3/ksgy4MaUL6wD+iT1AK9XsOsAbkg5Jt52SVrjJCJZ\n" +
                        "0Aw8BQxWsXvct0JQV9TAlxp5rMg08CJwEJioYPcmsCvDuFtwf9WVaYzzICLgFzM7ACApye5qYGOG\n" +
                        "cVeRwb88iKRFP9CXsc+ygGwc+CMuLILIDPBXQN7kWxZM+1bCJPABcDRuWASRt3wrAj+b2WRIUQSR\n" +
                        "cWAN2Wc/DToie3DIzKZKD7kSkbQReA13mBUd2h8AjpUe8l6RbcAmLmbV87gVOudl1wBXRuwncfup\n" +
                        "Drexo6nOBPC3/93GwlSlzPe8Z+1eyq8GM8BHuJP/WeB4zH6/1z0H/BTT7fG6HbgzqiIuxcn+q5kN\n" +
                        "Al8GdCNeNxTQHfW6qiTgMkpRLhsiRZ/sTcA2SeuARuD6mL5f0rW4ANAR0z0iaa3Xra/2oqKJNODy\n" +
                        "q6Qc63bfQkjqNwcMU37i507kK1y4LRIjwD5gLCrMlYiZPZ/neFmQ+19L0gBwB+UHX16YB74H9pnZ\n" +
                        "yaiiiD3yCrC6gHFLOA80SnrbzOZLwiKI/AncFJDPAt9kGKcNV+hrjsnlWxmKIPIpcFdAPgXsJHxX\n" +
                        "iaMJeAZXDopjFDgcXQ0ohshnwHYW3rUbgOVmVnVVJHXjVjV+FZgHvgV+jPcp4mQ/DnyMy3ijaAae\n" +
                        "llRx/0iqA+7DrWq8Nn0KeN/Mfo/3y52ImU0Au4ncFTzqgQeBnZJCd/ESeoEXgPaAbi9wINQpiUhw\n" +
                        "Q2XAIdwFK344tgBPAi9JavWVe/dCqVnSY8B7wK2xfnO47Pnl6K2wKiTtkjQdLYF7eagaf1bSownj\n" +
                        "DEg6JmkuUFUflLRdUrek+yW9I2ksYDclabekVZV8Ttrse3CbrTc1+zA+Ac7gLkj9lN8A+4B7cMXt\n" +
                        "NYRD9ijwIfCumY0sygNJKyRtKDUvy7Qivk+9pE5JOySdCMx4CNOSPpe0VVJrGn8Tw6+ZjRFLzFKQ\n" +
                        "X42rKNb7sa8DbsOl4RtYmKonYQlwN67EekjSEVwt6wxuv8wBw2Y2U5XIIvEqLnS2+NZI5c97s7iA\n" +
                        "0AZcEZEbsBz3+W6zl13Ape5TuNC+hUgNIG8inSTPunAzOuzbd7gQPQa04j4k3QzcgotaXbiJKKEO\n" +
                        "l4iWktGoLjORaeAE5bN8jotlm1FcnD8F/ObbaVytdtQ7PodbiVlg3sxKEbH0d2zwTi7FBYCVuDOl\n" +
                        "HTdRK/zv8xl9r6GGGmr4D+Efca5QYv++3I8AAAAASUVORK5CYII=").bitmap,
                iconColor = 0xffd64800.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "识货",
                packageName = "com.hupu.shihuo",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAC0AAAAtCAYAAAA6GuKaAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAFjSURBVFiF7Zi9TQNBFIRnjBw4pQRkEgeW3AEmIreogCYQkSmECmiACLsARGRBAHIJ\n" +
                        "FIAEQ7In2T7frfdHexZ6X7ba2/e+nVvdSQsYRiM85CFJIwCXAKYATr1Fyalbtzig/BeABYBnkm+H\n" +
                        "+HiRNJK0UgAba0NYuXCShQeSXgObx0rL9Rr4vHqe+TsAk9TNBzBxPeOQNJb0HZFWStJyPcdtbm1J\n" +
                        "3wPoR+86nr7rHYakWWRKOZKumDX57f3kSfoAMAzebVWUZCUdWwPAJ8nzfRO14yHpFgnCGRk6lxpb\n" +
                        "SUvqAfhJ7ZYp6YoTkr9b9TcHkh4AnKV2Cfwj+liTvMlQp1t2k77oSqQNksut8ebAvc5jE19Wx63C\n" +
                        "9xs/Sky6FCZdCpMuhUmXwqRLYdKlMOlSmHQpTLoU/0L6vROLdmpOu9IvhURC8DtJmifeduZkvs+x\n" +
                        "6db0GsAVMlyRRbIG8ETysaP+hlGUP13zl4JJa5QqAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffd72324.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "BOSS直聘",
                packageName = "com.hpbr.bosszhipin",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAAC0AAAAtCAYAAAA6GuKaAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMfSURBVFiF7Vk9ltMwEP6GR7+mpcFUtOEE6z0BodiCitDQsgX9pmffY1toEuoUCSdI\n" +
                        "9gC8OCdY5wRxTvBRSH7IY9lrE8dOka9JotFIn0bS/CjAGd1A2hqI5CWAAMBAiWIAqYg8tDXXf4Nk\n" +
                        "QPIjySXrY2l1gq7JhiS/k9w1IKuxIzkhGXZB+MuBZH3kb49FNmCzY9AUS7Z5ZEgO2K51y7AjqS/x\n" +
                        "SRNuRLzU5dFs1yOMG+sSKYDXIpKWdXhWoTxH94Rh55w31iJ50+GRKMO4jF/heND4zjX6sbKLFMBb\n" +
                        "EUm0wHc8btA/YcBw8Fo7Z2n2d/nK4L2U2tJDnA5hwHAZ6kYf6VPDSDfo40El38BsUYYQwCvPwL9h\n" +
                        "UtABgHce+QbAokS+AbCyY0cALgokRfzxhGTkcTuRp9/IkadUEYwmiqZOn5GShyQTn1ujyXESFlHg\n" +
                        "kSkMyzpr30kyVvJv9vcPZYBYjb0keZst2JHtSK5pskgfcgt/7nxvkqxcANiKyIrkBwBfbftnkn9E\n" +
                        "5CfJLfJHaw+z/ZnVdvYzsbIBgPuS+XJ5d1UYd3EF4EpExtbaoZ0MAN6ovi8dMpckhyKysDqfAGwz\n" +
                        "IiRHIhKLiJZVoi7pCEBEMhKRMYAm9d6c5ARAKCJTGItm5CY0FUwkIlNLvv7YJMc1zjTt7xHJR/v9\n" +
                        "Wulc23ZfSntfMdfcGVsjd2FdS8c11jaznwHM9kYiMgNwZ9vvRGRmFxvAuLMrx3qpo78H8B7GXQL/\n" +
                        "3K8vuBXyD9gV1nV5kWPFQtLOfOFQKKMc+ZqqsKVxh74dyvF4KrjEyHsA37sGYAJHFlx0VE1hgkeZ\n" +
                        "vErXkFTBRZNewB/R+sSDiOQsrb3HokMydTHVDb7UNIEn/veIF5WpqRUWVtYjfvkK3LJyK0b/1t4D\n" +
                        "GNQqt2yn0qKyQ9z7CFeC5MrjL7vCqorbU481fVzKPUye0vyxxipFdpCusAcQVRGuBRYrkWOhUAUd\n" +
                        "Sjzgcc94zGM9sNOklG1aPWXF81ebxEOS0xbIT9nF3xeKfECTsDc5Niurc9CDUJt/yUUwdaC2XgIg\n" +
                        "EZFK33vGGS3hL0MTUFwCIPusAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xff5cc8c6.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "斗鱼",
                packageName = "air.tv.douyu.android",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAVcSURBVGiB1Zp7zNZjGMc/F29JpRwih8WSkVNT/dG8ySFmElmGiGVjw8JG/sgYs8gw\n" +
                        "M21mzGmsN1tGjsOwyZmcErJYGw2ziurtsA76+OP+vfX09HvOz9Ob7/Zsv999X/d1X9/7vn7XdR8e\n" +
                        "1L7q8eo+/M+g9lNHqP3JSOzb3UbVC3WAehJqr+42plGovWMXdLI30B84AGgD1gErgc6I2FIkuz8w\n" +
                        "COgNbAR+j4i/qumnaUTUA4E+mbEnA+3AscAJQD+gR4H4emAV8BOwANgbOBMYTCLRhU3AUuAzYF5E\n" +
                        "vNose/MInK7Od9fgW/XiPDsamhH1auDJ7PU5YD5wONAJTAZGNKK/DOZExOWFBXUTUScDHcBaYCKw\n" +
                        "P8k9jgCWAF8A40iEWoFvgNERsaGu1upeakc21X+rh6jnlXCFm9XvW+lq9ZI4UV1aoGhWQd3CgvIP\n" +
                        "1LnqRerMFhJRfRZShKmWxFjgXXZ0x/fUo4FpwIFZ2TLgzIjYnLUbXteoVY8p6rdVEVFPBd7LqVoL\n" +
                        "jAKuLSjr0UUiw8D6bayIDaRvZVNFSXVImWl9Uz0yp/xL9W71qOy52fhevcqUuypDbVP/qKD0xszg\n" +
                        "BUXlL5q+j41NJnFHnq1lw6/6MnBBFZxvAF4BziVl5z+BfYFbSVm7GJtJ7tCnCt1dWANcEhFv19AG\n" +
                        "1Ik1jtQy9V1Tlv+1guwEdXGN+i+vbPXOJNrUzho7qhYPZ31Uctli5LpUJSJ3tYCA6sqCPlbX2Pan\n" +
                        "cjbv9I2obSR/zPPtRrAVGE5a8Z4BvFWHjkMj4s+8ij1yyq6i+SQE2iPiO2Am9ZGAtCXIxQ4JUQ3g\n" +
                        "njo7KYWNwPCIWJy9L29AV8m8kTcjnzXQUTEWASMjYrFpKQPwVQP6Sq4SdiASEUbEBOA64LcGOuwE\n" +
                        "boqIYRHxg3ow8L46A7iiAb1962qljldfVrdWGVnWq9eU0PVRjVEqD1NL2dpW0NFA0h57ACnCLIuI\n" +
                        "N4A3svpjgEeBsaTM/SPwc/a8AjgbOBLopZ4DXEkKGlMj4g+Sy46ua0S3Y0VJImpf4BngItJqdhEp\n" +
                        "yqxX+5NC8XxgIXAcMCki5hYqUUeRDhQ+IvnxBcB4YB9gnPo0cFqDJCANXD7Ux7Jpm2XRatJ0kjda\n" +
                        "vT+T+UKdpk4okJmnvqYOKmo7tEnu1IU1phxXksgCdWu5YVAnq69nz5eps9V71Y+7ysu0fadJRMrn\n" +
                        "HvX8THDPMjJz1ClFZd+o68oq3y67uQlEJlXT0Tz1nTL1s9WRBe+nmRaV/SroHaU+ov7bIIn1al7O\n" +
                        "24Y2gIiYqH6qzoiIO3PkepICQBceBG6JiDUFRk8DhpGCw5Dsd0y5zmvArIgo6/7bYFq6v6Tel1P3\n" +
                        "vHpJ9jxGfSFHZnqDo14K/5oia1lsm66I2BIRFwKb1cfVwlPCX9gePtuBF4sVRcT9QMmE1QCmR8Ta\n" +
                        "ulqq49Tb1Xa1lzrIdO7aWz3VlDxLtT3FFM2agYV10y8yaojaJ3t+yPQdVXXMajp9fEr92nSoV+uO\n" +
                        "c4t6aFOI5Bi3Wp1bWXKndj3V5TUSGdMKDl0GHWY6732gxna31Uji/FZxKDRqoMlVPlGHViE/tgYC\n" +
                        "K9X2lpMoMvBx9UfTMv8sixKW6cD7wRpIvGbat+x6qIerT2SGdJqi1QfqkhoILFEv7RYCxTCF3Frx\n" +
                        "tumyaPeCaf1VDhtMK4Tr1cHdbW9ZuP0mKw8zu9u+qmHa45dCK+9Jco+DGkFnifLp1V787xZQL8yZ\n" +
                        "ic+7266aoX5YRKJD7VG55W4E09+NNN2TdKhn7cK+e3QtOfo3QdlBpmxecu/fCpi2FgeHaZneB/in\n" +
                        "6DZ2t4fpL1r7Aav+A1kpEaj/kLRHAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffff6c2d.toInt(),
                contributorName = "sddpljx"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "讯飞输入法",
                packageName = "com.iflytek.inputmethod",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATQSURBVGiB7ZpviBVVGMafN4TIAomUtD9bq1ZmJQaFftEsxJCgApEiCimDKDCKiP5Q\n" +
                        "uEKJEVJQ0QdpCSFKw4JIxaJWSqjQLUuLcLfM1N1c1nUlZD9Y/PowM3X2vWdm7r17996CHhjunDPv\n" +
                        "n+c558yZOXOu9D/+XbBGBgNmSloqaY6kmZIuk3S+pBPB8bOk7uwws98byaFuAEuAF4FvqR0DwGvA\n" +
                        "olYKWAR01UE+D13Aynr51DW0gA5JqwtMRiT1SDoi6bikQUmnJV0haVb6m4dtktab2af1cKsKJb2w\n" +
                        "A7gXKCIZxloObC7ooaKGGpOIlTkJNwALxxC3HVgHnB53McCTkSRbaOBNClwPbIrk2dioBC81rduT\n" +
                        "fKsj+brGGvSdSNBHG8S5KO9Tkbyb6g12fyRY0+Z7kueTx7pag0wFel2QO8aJcxGPmJibagnwsnMe\n" +
                        "t3uiCi4POC5bq3Vc7By/A84ZZ75lnF51nMrfAIB3ndOKJnAt49QO9AWcvihzmOtEbGsS11IAjzlu\n" +
                        "dxYZr3PGDzaRaymAQwG37UWG+wLDU8DFTeRZCuAt19AzsmsTAqMZkq4O/D40s8Mu0O2SRsxsR1ou\n" +
                        "fa6Y2c7Qzsx2BvFC//1mNujyTZF0VVr8RtInku4KTOZI+smrvcepvdtdX5XWHwyJUIyHU7uOrMIL\n" +
                        "BZ4P7JcH9cuD+lvSusku/nMVLUeyUgtxubuekfmySiFdEd+YkInA7sBvtuOy1tkfieUIDcJ1Rn+F\n" +
                        "gSqHkhOyKDimA2dWIyS9flsQ52BwvquE56FYsIHAoKrVmRdSYFcoJLV5gUrcGrHb4GwmSMHNLmlK\n" +
                        "cP51jIykr5Tc7Dv99UxYenpS0rCkE2Y2nEfe4RlJ8yVli7ROM/sgYnfMladLOjAhYihJE3Pqswdk\n" +
                        "bK0fWzOskdSRE2sUzOx0OuYzIRWNmeLcWPmMHOO2apKXYLuZVSUiB0dz6r2QHmn00DogKZup6hHS\n" +
                        "kSbvTctH6ogRoi+nPhQyZGZD0mgh3SoWUkbsIzMrfpmT5G74vUqG0OFI7+X1yDXBeU92Eg6t7uB8\n" +
                        "EjDXBTiZ/nZGgndWIyKCuZLuk7TZ1b9iZhVCgGslXRhU/X0f+R4JsUJJi2U4KWmBmfm5PVYXw5qc\n" +
                        "+j4z+yEoX2lmP+bYLnXlPVErYDiYn3uiRi0EsNU9Q+blGW50hr4FWgbgItfQ/cDZ2XU//X7uyjeP\n" +
                        "P8Wq8YikSUH5TTM7FbUEpgFHA9X7m0KxBCRfIf8IeI0As8qcOhiN65rEt4iTX1C9Xo2T75WWfQpK\n" +
                        "+SxzIo4SrAzLnB8KHMf23XUMAOYBg05Ibd8RgDdaKSSdpfY5Ee/VE2gKsLeFQj52InoB/8JYdbAF\n" +
                        "wJYGcyzLuRT4jEoU7oSV7iECyyRNljSkZHv5mJLXiuMNYf5PnqmSnpa0KnJ5oZn5Z1xdSWYD+10L\n" +
                        "NWRrGTgvnVx6Ir3QQ6O/rQFtVH5pybAbWAvcWEO8+SS7YX05Md8HJpVHSlDz9jTJ+9cTkm7IMTkl\n" +
                        "aZekX93Rlh6XSLpU0uIc/34l29Pra+VWF4DHgV9yWrMejJBsH7Q3RYATcxbJRsxY/gHxPfBsSwTE\n" +
                        "AMwhGfN7GP2K4zFA8r+Vt4Eljcrf0H8HeQDTJF2Q5umX9JuZ/TmeOf/z+AuQV+ZFLOIPXAAAAABJ\n" +
                        "RU5ErkJggg==").bitmap,
                iconColor = 0xff4679e9.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "讯飞输入法小米版",
                packageName = "com.iflytek.inputmethod.miui",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAATQSURBVGiB7ZpviBVVGMafN4TIAomUtD9bq1ZmJQaFftEsxJCgApEiCimDKDCKiP5Q\n" +
                        "uEKJEVJQ0QdpCSFKw4JIxaJWSqjQLUuLcLfM1N1c1nUlZD9Y/PowM3X2vWdm7r17996CHhjunDPv\n" +
                        "n+c558yZOXOu9D/+XbBGBgNmSloqaY6kmZIuk3S+pBPB8bOk7uwws98byaFuAEuAF4FvqR0DwGvA\n" +
                        "olYKWAR01UE+D13Aynr51DW0gA5JqwtMRiT1SDoi6bikQUmnJV0haVb6m4dtktab2af1cKsKJb2w\n" +
                        "A7gXKCIZxloObC7ooaKGGpOIlTkJNwALxxC3HVgHnB53McCTkSRbaOBNClwPbIrk2dioBC81rduT\n" +
                        "fKsj+brGGvSdSNBHG8S5KO9Tkbyb6g12fyRY0+Z7kueTx7pag0wFel2QO8aJcxGPmJibagnwsnMe\n" +
                        "t3uiCi4POC5bq3Vc7By/A84ZZ75lnF51nMrfAIB3ndOKJnAt49QO9AWcvihzmOtEbGsS11IAjzlu\n" +
                        "dxYZr3PGDzaRaymAQwG37UWG+wLDU8DFTeRZCuAt19AzsmsTAqMZkq4O/D40s8Mu0O2SRsxsR1ou\n" +
                        "fa6Y2c7Qzsx2BvFC//1mNujyTZF0VVr8RtInku4KTOZI+smrvcepvdtdX5XWHwyJUIyHU7uOrMIL\n" +
                        "BZ4P7JcH9cuD+lvSusku/nMVLUeyUgtxubuekfmySiFdEd+YkInA7sBvtuOy1tkfieUIDcJ1Rn+F\n" +
                        "gSqHkhOyKDimA2dWIyS9flsQ52BwvquE56FYsIHAoKrVmRdSYFcoJLV5gUrcGrHb4GwmSMHNLmlK\n" +
                        "cP51jIykr5Tc7Dv99UxYenpS0rCkE2Y2nEfe4RlJ8yVli7ROM/sgYnfMladLOjAhYihJE3Pqswdk\n" +
                        "bK0fWzOskdSRE2sUzOx0OuYzIRWNmeLcWPmMHOO2apKXYLuZVSUiB0dz6r2QHmn00DogKZup6hHS\n" +
                        "kSbvTctH6ogRoi+nPhQyZGZD0mgh3SoWUkbsIzMrfpmT5G74vUqG0OFI7+X1yDXBeU92Eg6t7uB8\n" +
                        "EjDXBTiZ/nZGgndWIyKCuZLuk7TZ1b9iZhVCgGslXRhU/X0f+R4JsUJJi2U4KWmBmfm5PVYXw5qc\n" +
                        "+j4z+yEoX2lmP+bYLnXlPVErYDiYn3uiRi0EsNU9Q+blGW50hr4FWgbgItfQ/cDZ2XU//X7uyjeP\n" +
                        "P8Wq8YikSUH5TTM7FbUEpgFHA9X7m0KxBCRfIf8IeI0As8qcOhiN65rEt4iTX1C9Xo2T75WWfQpK\n" +
                        "+SxzIo4SrAzLnB8KHMf23XUMAOYBg05Ibd8RgDdaKSSdpfY5Ee/VE2gKsLeFQj52InoB/8JYdbAF\n" +
                        "wJYGcyzLuRT4jEoU7oSV7iECyyRNljSkZHv5mJLXiuMNYf5PnqmSnpa0KnJ5oZn5Z1xdSWYD+10L\n" +
                        "NWRrGTgvnVx6Ir3QQ6O/rQFtVH5pybAbWAvcWEO8+SS7YX05Md8HJpVHSlDz9jTJ+9cTkm7IMTkl\n" +
                        "aZekX93Rlh6XSLpU0uIc/34l29Pra+VWF4DHgV9yWrMejJBsH7Q3RYATcxbJRsxY/gHxPfBsSwTE\n" +
                        "AMwhGfN7GP2K4zFA8r+Vt4Eljcrf0H8HeQDTJF2Q5umX9JuZ/TmeOf/z+AuQV+ZFLOIPXAAAAABJ\n" +
                        "RU5ErkJggg==").bitmap,
                iconColor = 0xff4679e9.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "QQ阅读",
                packageName = "com.qq.reader",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAMRSURBVGiB7ZhNSFVBGIbfCVIuBG4UMgiSgihq5dZqY5ERSOSunRtXgYs2tW3TImjh\n" +
                        "tkWbqHDRIrAfI7CltErIwMJIJfuRDOEKGjwtZvTe5J6fb+7Rg+ADlwv3fjPzvjNz5nzfSHvs0RSu\n" +
                        "yM6AbklXJJ2RdExSh6S/kpYkTUl6I+mJc26uyHGbBugFJsnPI6CrbN0CKsB9g/B6qsBQmeLbjbOe\n" +
                        "xEgZ4isFiS/HBDBSoPgNru2U+O5tEA+wDHRa9eyL8HA7ok0e2iTdsjYyvQeAw5K+WgcxsCqp0zn3\n" +
                        "J28D6wr0G+OtVCRdtTSwGjhnjI/hrCXYauC4MT6Gk5Zgq4GDxvgYTCeR1UCLMT6GVktwzDG63eQ+\n" +
                        "gSS7gZ/G+Bi+W4KtBiYkrRvbWPgo6XEhPVHL8R8Ag4T8HZ/I9QJ3gZkm04c1YAwY2kgj8FluP3AH\n" +
                        "GA9jDMYYmGsw4Bw+/+8HKiGuG5/c/TAIfw8MA+2hj54geDohfiXGQJagKvAUGMCvSgvQh5/RRqzh\n" +
                        "K7Hu0P/RILrRRG1lOcbAeI6ONwfAr0xPnbgRYCUYHaG2BQeACUPfAGMxBvqNg2wwDdwEOoG28GkH\n" +
                        "bgCzkX32JelsmI1SKy46JN0zu68xL38rcaSJPq5LWpTU6px7mKsFfm/PACfCzJXFcNAwA4zmtkyt\n" +
                        "3q3i9/NCCeJngS78cwQw2Uhr0ovsV/iuSOqV9CG3++J4J+mCpANbNP1HkoH6p35/gaKs1I+d/yTC\n" +
                        "n+mjYeuUvYUWgpa4TBh/JJbFcJa+xKIenyp8knQoynlxzEs6lVTop95KAFX5B7lM1p1zidsnK51e\n" +
                        "KFhMDKn1QZaB3wUKieVb2p+7YQVSS8wsA0sFCokldRKzDCwWKCSWhm/gDXaDgVQNWQY+Fygkli9p\n" +
                        "f2beTuOrrMuSLkk6XZCoLKYkvZL0wjn3Oi0w5nr9vKQ+SRdVyxSbZVXSS0nPJD13zqUenYWAL+QH\n" +
                        "8IX6SnI6k8gK/spm84ajNOrMjOKLoCSqIWagdNFJ4Iv4QeBtnfCJ8Ftb2fpMBDO7S/RO8w+xh352\n" +
                        "EqfboQAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xff45afff.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "QQ浏览器",
                packageName = "com.tencent.mtt",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADUAAAAyCAYAAAD845PIAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPhSURBVGiB5ZpLiB1FFIb/I4nR8REw0UkGE9zERyIDxriIbgYXQTKuXKhBERcujAYf\n" +
                        "oAshiwEloLgQNA+UoKCiC40YRBEXCYoIEiE4YzJxQPJkzEtEGXQw8LmovtjpdPf0qep7r+gPDUPX\n" +
                        "qXPOV9W3q+r0SP9BWTecAssl5a/fJB3PrmNmdrobcVsVsBzYCHxCM50CtgKjwEC/8z9PwDpgd0OQ\n" +
                        "OsDtwM39hlkLvJMIU9QM8CLh0e050JaWYYqaBh7tFcwA8H6XgfLa3m2gNT2EyeuzbgEN9wmoo21t\n" +
                        "Ay0FzvYZCtr6jQHzga/6TZPTA21APd9vioKmafi6v6gCaIWkx5NHRpqV9J2koy34WqKUnIBXEkZ0\n" +
                        "J/AwcGvB5yBhB/IMcDzS9wwxOw9gNXAuMug9DWMMArsiY/jXL+C5iEDfAAsiYq2LiHUWuMwbaI8z\n" +
                        "yF9emEK8FRFg99b5PO9FASyTNOLMa5MXJC8zm5L0tLPbaGNL4DHniO1wJlMX+wtH3DN1voqv9Ouc\n" +
                        "ubS56fzYYbsIuLqqsQh1pcPxjKQfHPZzadJpv6yqIQXqgJmdcyZSp/1O+2urGopQCx1O25wlmdkZ\n" +
                        "+XYejaHmOZxOO2y74bNyAopQPzucLnXYNtUqh+2JqoYUqCGH7ZwCrpJ0uaNLJVTxcfNM/wiwwMxm\n" +
                        "HX0ELFFYOi6RJDPbmzXdqFDsrPytFFQJVQx4v3Pxva+Bz4XAZmAf8EuJj6PAy8BdwLzMdrZB7Cua\n" +
                        "Qo04ob4GKh8ZYBMw6fC3M+t3C3Cyxm6iEVAukXEnWOmuAnjX6aej01n/0RqbMS/UxohExgo+tkbh\n" +
                        "/KMvMz9PVLSv9EINEndIHMv6vxBLUtBLmb/rgQ3ANuBXGjx6F9QozOykpLdcIxE0AAyqndqGJD0L\n" +
                        "HJH0mqTbJX0kaY2kD6K8AesjRvY24JGWZqlKrxLOfHHKHDTVt1mf1M86TbQPuDQF7OAcASaAp4DF\n" +
                        "hM8wvdIbKVAraxxPZO3rqV9TuqUNVXmXFjM7MrMDkp4saZqV9JDCNut1SddEj1y87kzqzYU7jS3Z\n" +
                        "/bf7MEMd/ZQ8LIRPOp3y2U3E1ezaVunxp/bxy8vMvpf0oKRdZnZQ0nDySKXrhrKbjaEkycxOKIBJ\n" +
                        "0h2pGbWgQ2U3XVCSZGZ/ZH+eSkonXXvNrPT854bKaTyhbxvaXdWQAlU69T3Spwp7wvYF7Pg3vfXa\n" +
                        "BPuwhzAzwNquAuXA7gamSpL4E3gT+HyOZPcQzkx1h8v3mubT2r/GEWoVw9k1X9IxSfvN7HDWfrHC\n" +
                        "Z6IhhZrh7wrVq3Ez+zHnZ7XC+jOkUK+flDSVLSf/X/0N9IzKpFVJbfwAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff6593f1.toInt(),
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "铁路12306",
                packageName = "com.MobileTicket",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAQuSURBVGiB7ZldiFVVGIbf1UyWKTTRz4TaqIERXRg5RRFFiAPlRYZRVAYR3URENF70\n" +
                        "A0XRRQVdST8gQReGRjdKEVRKEf1oaRgEKVYUijOFUGY2Qzgy83Sxz+Bun+9ba+999pkR6oUD53xr\n" +
                        "7ff93rX/1vcd6X/8BwAEYAMwAowCh1vfjwAfAufPdo6lACwljgeb1jyjacIWrkiMD3RJt3MAVwLP\n" +
                        "A18C44kzMgF8B2wEVs527pIk4Gbg3UTiKewGHpgtA1cBOzo0UMR+4I6ZNPFMwwaK2AzMrZJTqGHi\n" +
                        "fUmrS0w9KumQpF8kHZd0jqR+SZdIWlji+J8lDYYQjlXNMQlga2IlvwdeBG4E5jkcPcByYBj4IsH3\n" +
                        "A9DXtIktEcG/gIdr8q4B9iUW5+ymTLwQEXob6G9A47mIxs4mTNzrkE8CD3Us8G+tIeAnR29jJ8QX\n" +
                        "RVbp2gY95DXnk90bFtbWJd3mEK5pOP+ibj9w0tA9AlR70gIDjok3u5R/UX+do39fVaKXDZITwJwu\n" +
                        "5W7lcMjIYV8VggAcM0g+6GLeVh7POmdleXGut42/XtK5RvytRjNN43Unflsx0OtMvMmITUjaFlMF\n" +
                        "eiVdOP0zNlfZIo6FEI57E0IIvwK7JRWfkNcV53pGrMJobwhh3BMFVknapGw/lTIhZfu8ceCpEMJr\n" +
                        "kXmfqN3IguIk79JabMT2JxJ7RdlmsFfSmSU+vcou31eBiyO8B4zY/GLAM2I1B45GxCTpgsR4DLHS\n" +
                        "9w8j1rbF94z0GLGJRDJjifEYTkTGLN22vD0jfxuxttM5Q7DKgZPFgGdk1Igt6SSbDmDptj10PCM/\n" +
                        "GrGrE4JmIVUSZ0XGBo1Y233jGdljxBZab9Qc/oyMpWBdAdMYMmJtT1DPyMdO/P6I4LCy2rwKpiQ9\n" +
                        "GUIwjQC36tQLNo+PSisAu4w9zkjimPOAZcClZG3TJcC3ueNvABa3xpcBixJ8VrtpAqcf4JF42+gy\n" +
                        "HZQ8T77BUHrnDPQBU4Z+9TIC+M0g+p1sT1WWY0/u2NI9X2C7s5CX1THi1es7umkEeNrRrb/7Br5x\n" +
                        "SDd3wwjwiKM3SSc9LmCRQwzwHhB941cxQtbN93BnbRM5gbURgcPA7YD5UksZAeYA1wCfRjQ2dGwi\n" +
                        "J3hLRAjgjZpG1id4n2jMRE50ZUTwrppGLo9wrm/cRE54iKy/lIfblEgZac2xbvBHu2YiJ9wHbMqJ\n" +
                        "uhVeGSOtedOLsxNY0Z3MffF1wHBizuc5I24VCawGHms+y4YAHChz5k5rAI8Xrvt3ZjunSgDmAi8Z\n" +
                        "NzBkL9GlTWtW/g/RA7BA0gpJqyTdLSl2GU1K2iJpu6SvJB0MIUx1ol/JCDAo6R6d6knNU1b4DLQ+\n" +
                        "pXfFBYxKGlH2x+mYsmpzQtKuEMLWmpw+jGu+2/isbG5VV/CgssJ/suJxddAj6esZ0Dm98A/9mxbr\n" +
                        "obOIEAAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xffd0070d.toInt(),
                contributorName = "sddpljx"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "中国移动",
                packageName = "com.greenpoint.android.mc10086.activity",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAXfSURBVGiB7ZprbFRFFMfP5amoVFsxVgSKGuQRQySgHwz4ohEIiohRTDASAVGIiRFM\n" +
                        "lKcPDEaigoIBQYMgimJVUKkRpYrhWYME3y0k6CpEEaEBUhHh54eZ6w6zM3N32V3kA/8v7T1z/uec\n" +
                        "/925d87MrsgpnFyIihEUKBGRwSLSW0S6isiFInJURFIi8p2IrBGR5VEU7S9G/rwBtANeI3ssBjr8\n" +
                        "33UfA2BsDgJsTMo3f0GmFrBCRG70DO8VkQ0iUi8iDSLSTETKRaSXiHQz/H4UkcooilKFqClnAGs8\n" +
                        "d3kr4BMXc9sBTwMHDF7liao9LqIMqHcI+B0YkGOspsB0I8b9xarbTnwpsNchohrwTlfgdKAr0B0o\n" +
                        "dYx3AXbrWM8WW0QPoNEh4pUApz+wCjho+B8BNgPjgVaGb0ugVvvMKpaIWxwCAKZ7/FsDGzwcE4eA\n" +
                        "uyzum0k36HhF3OkpYoLHvwJIZSHCxMtWjOXaPqNQIiZ5Et/q8e8F/B0oeDvwHDAVeBHYaYyts2It\n" +
                        "0fbJ+YqY4SjkHzyvSWBAQEAKGOjhDQcatN/H1lj8ir/teEW84ShmJ1Dh8R8TELEoi3xnkn7QP7fG\n" +
                        "Nmt7z1xFfOYo5hvgHI//wwERz+SQtwmwSfOqDHsJcBTYnauQKVYx1QHfeQERIzycUmAUMA0YiLX+\n" +
                        "AO84xHTUtpW5ihmhiUsCPssCIgZ5OHc7fHdhTRsj9gLDNlTbhiUVfznQ37i+Gvcq3AL/GtEI9PLE\n" +
                        "T2rzx1n+z2v7TMM2W9tKQkIuQvVL3ocKOB/Y4SkkBZR7eEsTRMSYa/HmaPskw7YNqPEK0U4LNPFc\n" +
                        "x5jZD9kIvQxCU9CF5RY/foMO1dcVwH7g+iQxf2jHpoatVNtdWB2I9VYWhVehpnEl8JW21VhxqrX9\n" +
                        "Mn39UChvTOqkSfVAM8M+0lHEe4E4bycIOAz0dfCq9LhvPWmqr6e6kvbFaDuAIZq0xfIbYhQyLyBi\n" +
                        "RYKIfUCnAD+ejhs5dmbsiQUCTVzErsBvQHfD9pgOtt7yfQBYHCiimjAOoKdICKSfjQ8NW4m2TQwR\n" +
                        "ZwB/mUqBWZq4lsDGSfuGXssxdgDnefhPktnSL9K8ZYatD2pz1zFUzBag3rLFXeiXITHAugQRKeAC\n" +
                        "D3ei4XeNNRZPs0cM20jgZlegCfpva01aZY2/ru1bMHZ1eqwtUJcgoh442yNilsO/n+UTty03GbbM\n" +
                        "mwp8AYzW//fWpIWWz3xt/wUYjNp/T0NtXUPYjqND0DFf9XD2AW0s3/gTd07N2GkQ8CdQpq/v1aTZ\n" +
                        "lt/MhKJt1AGtPTlXejiH8RwnobYSm7xCtNMmjNctaicH8LjlNz5LEdtw9ESodn2jh9MI9AjUeBpq\n" +
                        "sR4eElJuF076o59s+foOI2J86xHRFvjJw/kZx8sAdZTU2bi+AU93bZLu00HNLjg+CJhj+bYHvnYU\n" +
                        "NCcz8n/d9UGHP6jZ0MLDe0r7DDFsyUe+pB+qNoatRttecvhfp5NNALp4YlZ6BAB8FKhlruU7MlGA\n" +
                        "QW6JWhj3AM0dYrw7Rk+80Gn93ADP1SHU5pJbgGs18RPD1hy1jmQtBng0IMJ5ZgW0It0gmlhr3thc\n" +
                        "xNyjA7xr2FqQPiBI4dkToPYutRmlpDHaw+uM2vraqHL55yImfgWb280zgO+NJJtRz8doYBywOiAA\n" +
                        "/Id7V6DWEBvz8xJhJFhoBwQi4IOEgm0cAq7y5LjDw5lSEBFGonh/8YJln+gpwMavwCWe2KM8nAcL\n" +
                        "KsJIGDeO1Za9HNW6uOZ2HTAmENM+Q4uR2dkWWEx8pPMDmU1dhHpY+6HWjfYJsWY7JTi2v0UB8ISR\n" +
                        "dOxx8LuhTl1sNADdkiMUEMAwo4BdqG7Z2WIYnC7A+55PYbv9CWeLvL+eRu3vPxWRMm1qFJG1IlIr\n" +
                        "IjtF5IiInCUiF4v6JYSzfRGRrSLSJ4qihnxrygtk9kO5YGZyhhMIoCfuryN8WA9cWYjcxfpRTQcR\n" +
                        "uV1E+ohIJxEp1bn2icg2UVNvaRRFdcXIfwonA/4FX00tlTStjbEAAAAASUVORK5CYII=").bitmap,
                iconColor = 0xff008cd9.toInt(),
                contributorName = "sddpljx"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "中国联通",
                packageName = "com.sinovatech.unicom.ui",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAY2SURBVGiB7ZhrbFNlGMcfQIl4iY4AkoWgQQQ08Y5R8IOJGTHGqPgBgvGGm/HGJaIB\n" +
                        "ERUxLEbRSCIRRYfxLixx0TGcwJgw5mCXtqvbyirLZO0ubWe3Ysew7eDnh57Cu3fndGWBRpP+P/U8\n" +
                        "7/95nv+/5z3vec8rkkEGGWSQwf8VwKPALiAMHAd2APcm4d8MfAkcBQaAamANMC6dulVB2cCfWKMW\n" +
                        "GK/lfJyEfxyYl24TE4HOJKIS8ABZRs43KfAB7k6nkR9TFAVwAPj6LPhN6TJxk965oaGhuGxn2Qyb\n" +
                        "zXabx+OxDae0L9zX7nQ6H3A6nVNsNtumaDSqU+anw8iLasdjx465NMqoioqKnVYmOjo6mrdu2pSt\n" +
                        "JlRXV2/XaBvPRtPoEXq5Qr3w+Xx/aOOUl5e/YZXsdrnW5i1b1qnG+sPhGo127Qi1pQ6TO9KxePHi\n" +
                        "ixLjVVVV46PRqDvJzOpv/7P9ZrVm29GjezXO6+kwcvUQZf39exPj/f39niQmTuPw4cMzREScDsdm\n" +
                        "k+Gc827EMLNR7+z3+3eLiNTU1Lyuxo8cOVLqcDgGrVrdweA2EZG62toPTUwcTIsJw8ho4Hddgcfj\n" +
                        "+VlE5NChQ0sA+vr6mhM5rsbGHQA9PaHNIiL7f93/nomJ48DMtBkxzFwCOHUlbrd7h4iI3W7PKysr\n" +
                        "m5Pg5+bmZjc1NS0XEdmza5eZiR5g1rkSNxZ4HPgU2E58T5QLXGjBvwAY8t5oaWkps+pRX1+/xcSE\n" +
                        "D8i2ygEWGlq2Ad8SX3AutSLfCLSZNAEIoO2DgOuA6cbvWj2htbW1asGCBWPUnLq6uu9MancBFwMT\n" +
                        "gNkmmlwWmv4G7tNN5FiQdTxp8McA7UbsSiNWrZPb2tqqEj1cLpf+0gPoAMYa+ceM2FTj+q4UNeUl\n" +
                        "TFxG/NamipeAX7TYLVZm6urqiiorK78wqdNp5FwKdCvxw8By4s9MKhhIGFmljwQCgQJHjWNuQ0PD\n" +
                        "+ykW26DcXf3lZgY3Z3bFi1Np4G5u/srhcNzldrvf1scSjQftZFtbWz9Sp11nZ+ejw/T4RJumo4Cy\n" +
                        "YUxM0HJeTtagt7f3TZXvsNlWmhkZ9ICXlpYuEg3VBw/mW/Sw6Vyj5tQkuuZa5Jh+GjQ1NW3XuSUl\n" +
                        "JbepnMSmsV0lxSKRa9TrN1555YaZs2Y9Y9ZcRG4FlpjEV1rwRUTWAKM0Ew+KyENm5GnTpj386ebN\n" +
                        "g1Ynr9c79H0DbFDdxWLRUxUVFc/Z7faJ9fX1C0OhUF+SfzeBd5V6W1Pg71X4w01dAJx2+3K73T6x\n" +
                        "qqJifiAQ+EsdSxSaAkRSKWZgK0Pf6K8atb7UyT09PQ1+v3/IagZUGzlztHg78RfyP6kKUu9K0odN\n" +
                        "wVsGPxs4YcSetDJxCoIlJSVZIiIBv9/syzFhZrYSm2vEnkhR03p9ii0fJuFVjT8PeNz4vc2E3+P1\n" +
                        "ek9/IH2wYsW47u5uexIz9wOPaD0eG0bTGrPnSoAbgI84s7NtBj4BrjNNsDbRUVhQON6M7/d37Tfh\n" +
                        "16ItAEr9bOAdoBIIAnXAFuBGK01q8gXAFVhsFhVesYmo1sLCwjGrV6+elpOTMzXB9Xq948pLS2eK\n" +
                        "iAT8/lKTPCdwSZJeo4jvyUb6iW5Z+CcTMR4RkddWrZoNYLPZdiX44XB4LcC+ffuWiYiEQiEzMy7g\n" +
                        "Ique5xzAUl1BMBjsEhHJz8+/8+TJk2fmTG3tRpfLtUTlHjhw4AUREZ/PV25i5vt0mRgP9KqdQ6FQ\n" +
                        "cEFOzuUiIrFYzGsibgiefeqp6416ZnuzOclVnBsjj2hNY3l5edMT49Fo9A7ih9OW2LNnz1K1pt/n\n" +
                        "a9AoK9JhZJ3asbGxcYfOcTgct1uZ+Lyg4GmdX7Z790sarfhsNI306T+pXoy9cOwEnZCVlWV5ED19\n" +
                        "xox79NikyZMnaqGuEWpLHcCd6l8XiURobWlZLyKybt260b29vZ8lm1YAHR3tlUVFRZNERHYWFy80\n" +
                        "oeSedyOGmd/0ztFoNBKLxYacRhNfUofstQYGBgYikcgJE34Y4/M5HUb0jZ4VIsBVRk5KJ5Ck624o\n" +
                        "ZhYNI+gEcKvCn4DJOZiG83/ma2HmSqCA+OFFzBDfBqzHYisBPE98DxcmvkwHgR84V4dzGWSQQQb/\n" +
                        "efwL+suoeDf8fx0AAAAASUVORK5CYII=").bitmap,
                iconColor = 0xffed1a34.toInt(),
                contributorName = "sddpljx"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "权限管理服务",
                packageName = "com.lbe.security.miui",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAO2SURBVGiB7ZlNiFZlFMd/p6K0xlkEBY0RUoFDaLSwKBcFLXKy3EVEhRFRQWFEMVBE\n" +
                        "FNiihSAUFWm0KExa6Kq0aSWEKW76xgIjYjKtBFNxYcPMr8W8b7zv837de9/nzrjovzvPc+75n/99\n" +
                        "7nm+LvyP7lAn1Rn1G3Us6Vumfuk8tqgXLVaePaHepf5mO95KfF5I+k+p9+fgv2DYAOpN6gFgClie\n" +
                        "dI8l9uWJPQp8rH6nrh0mj8pC1FH1Q+Ar4NYebnPpYz38VgH71c/Uq6vkU0mIugk4BTw8yHWAnWId\n" +
                        "MK2+WjanUkLUu9XfgTfKEpXEK+pp9cGiDxQSoq5U9wN7gKtKJDRoBPphGbBDPazePMi5r5DGdLkT\n" +
                        "+BGoUozDCGliHDik7u1XPz2FqC8Bp4EHMiSTAxPM18/WbutPhxD1BvUo8FoG8nREIkPMZ4Ez6h2t\n" +
                        "jW1C1DXAD3TO/1WRJj6bKe4SYJ96T7MhHZH3MxE1cSaxz2aOv00dgRYh6mPA6sxEXyf2kczxx4Cn\n" +
                        "oH1E1mcmmQM+Sdp2A9OZeTZAu5AbMxNsiYhfWhsiYgbYmpnnmjZLnTYfpvoxqx9k5PoT2kckx9QI\n" +
                        "sC0i1vVziIiNwOZMfO1TvJ1nibI4qK4qlYFe6/yOdxj8kUvIt+qKoV6pXtF4EZWFDH2wAs4Bfw8Z\n" +
                        "4wRDrjE5hKwBTqqfmpzRB0Fd6vzhbA64M0MuoB6tOLQpnivI90gmvo4aySVE9d0BIjZn5DqeBh92\n" +
                        "1krxZA8RE5l5Ooo9xyGoFZM92l/OzAPUsyA2cZ16b2uDegvVTpr9INQrBGBlYo/XwAHUL2RJYi+t\n" +
                        "gSOgfiFl77WqoEPIQqAOIR01UhtJC2ob9bqFXFhDzK6o+9O6uOb4sEjFXhtahfxTQ/yZGmKm6KiR\n" +
                        "wzWQLMSvtePQLmTPApDWgSloF7ITOJmZpO5PaxbYDi1CIuIE8HhmorqLfVNEHIFk+o2IXQz+nXa+\n" +
                        "4OmIeKdpdKwjEbEDuJLGt3ce4gBwfUS83drYdUGMiL8iYgK4HfhpAZIrgmPAhohYGxE/p519V/aI\n" +
                        "+CIixoGHyP9LoChmgWciYiwi0kvx/1BoixIRH0XECPB6ruwKYjtwWUS8Ocix1F4rIl4ERoBdFRMr\n" +
                        "iilgRUQ8ERHnijxQetMYEWcj4j7gNuD7ss8PwDFgfURMRMSvZR6svPuNiIMRsRp4lN5XppcU5JsF\n" +
                        "Jht1sLdqTllg90u35xOfjV183lMvXay8u0Jdru5uJHhIHU36Q/280b9PzXar8i8xhQwm5td3xQAA\n" +
                        "AABJRU5ErkJggg==").bitmap,
                iconColor = 0xff3b8fff.toInt(),
                contributorName = "sddpljx"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "阿里巴巴",
                packageName = "com.alibaba.wireless",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAVlSURBVGiB7ZlpqFVVGIafdb2mXrPBCXPITNO0bCLTSJsl0KQ5KtIKDcOSKKLUTEqK\n" +
                        "osj6FUGlNEo0gZEVWTRoSdGoYYMN2jUtTCuH0qs+/Vj74HZ3zrlnuBrG+f7ss/da3/u+37fWWevb\n" +
                        "a0PNalazmu1NFvYUkdoKGAwcDRwJ9AH6A71yXYBfgW+Ab4H3gAUhhI17SmNBU7urE9Tn1XVWZnPU\n" +
                        "jv+F+DbqFer7JQp9ST1VfVXdWqBPk3ringqgv/qEuqXMjP+m9kslYZT6Yp5+m9TeuzOAger8MsWr\n" +
                        "blTfUReq1+fB7aLOzfi8uDsC6Kq+Uobw5erj6mR1qNq2RJ67Uhhr1YaWDOKGEoT/apxq49VDq+A6\n" +
                        "OIPbpyUC6KwuKSJ+g/qwOqRqsp2cA1L4m9RO1QIOM87rrP2eTJmRaovvS+rUFNeH1YKNLTACc1pI\n" +
                        "byHeLur6FN811YDdUWQqfV9sFNT6akbJXVfDLyrFQb23SBAa/9AHFPHvo15pLE/K5Z6Z4Tqn0iCm\n" +
                        "WtxWqz2awbg76XttmdxjMlyPVxrEhc0EsUxtU8C3QZ2irjROjavVh8vg7qtuTnH9oLZrzu9f81c9\n" +
                        "HvioQP+NQLvk+jawAVgLtCJWsQOB9sAaYFYIYa5aB/QAGkMINhNEW+AzYEDq8UkhhPebCyQf2KdF\n" +
                        "RmKxuo96bfJ7hdqofq2+q85SBzTPUpD7rQzfDZUCFVuhVJdUKrIE7tkZrgcrBRrcTBCqy1pYf477\n" +
                        "9gzPa9WAPVdCILe0oP4c71UZju+stDA0luLF7GlLWDkq4D03w9OkDqoG8OYCATSpZ7Wg9jTnQHV7\n" +
                        "hm9EhVgdcj8KTavTWlT9ruQLM1w3l+nfxlioLlDNPVyQJ4jHdksEke/KDFdJdZQ6SL1RXZTy3ape\n" +
                        "V5/0+SOP3+oqxdYBDcDmEMKOTPNlmft1Kb9WQCegJ9CbeGR0FDCCnUdHAN8BM0IIz6RJJ+cZkV/U\n" +
                        "nuWKV89QpxmPgQ7J06fB/EdDK9WPjTXctjztGv+zj6qHZ3FDKgvLgMMy7VuA54EFwHJgPbAJ2BfY\n" +
                        "D+gM9ANOBIYTR2AZMA94KoSwskDAPxKzXYptB+YDjwEv5xndf4H3ML5fVGqvW+LioE5sBmuF+oh6\n" +
                        "kbpfKZj5isYJwFhgKJCvwt0OfA98AiwE3gwhlL3jq0cDFwDdgK1AI3HefxpCWF4uXrG3uwbgcKAj\n" +
                        "8Vx2A7GqXRNC2FouUc1q9n8w9Xj1S3Vm6tkE9T7jrrpYPUydpM4oEfMe9dbdpzo/afosd0jy7Jlk\n" +
                        "I+pvrII7qR8YPxu0V4/NYAxWT0jdrzIeVjeoR2T6HqcOLaDlYvXs5HdQL1fPTLV3V4cnGobWpxpG\n" +
                        "A6OAg4A3gPuBk4EmYAdx5eoB1BOXyjHATcDt6pMhhHHqA8SVbpvaPoRwOvAVMAyYAtymPhpCuDrp\n" +
                        "2xuoVw8ETs6906vTgCHAOnUpMBI4BThA3QT8CdxJXK4vBUJdKgm5l6a+wO/E2gbikvsXsH8C1gVo\n" +
                        "C/wcQrgDOBcYqx4D7MPOPeE0Y4myHfgphDAjCXyC8Z2jXZKkRmJVcFISxHCgZwjhvBDCeOJnukEh\n" +
                        "hMuBcUm/84A3gZmJtul1ifOoRPjnwGygdSozTYmY3J5Tl4jtnNwfkvQZCUxKstSUtLUG/ga6Jvfd\n" +
                        "kutoYCKwNIWbmx0bgA6pBLchlj4Q97T2xCJ3ffLsD6Ap5zwe+DaEcEzOW30IuIT4UXIxsBlYmVy/\n" +
                        "BPqozwIXAzcCTwLTiSO6iLj7NwFLgF7qC8D5wHXE+m0K8fjoC2AFsMr4RaoReEGdTzx2mgzsUOcl\n" +
                        "SbyJWOd1J075NUmia1azmtVsL7J/ABsA9klaFnncAAAAAElFTkSuQmCC").bitmap,
                iconColor = 0xffff7300.toInt(),
                contributorName = "sddpljx、moonbai"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米智能卡",
                packageName = "com.miui.tsmclient",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAARZSURBVGiB7ZldiFVVFMf/62olSQQZKFZThg+lEQ1jDzOaH5VNhRFED5U9mGQRQh+D\n" +
                        "EfRk0OdLUBBFRghiIQSmmTJiNKUOTamVDhZTKVbiS2lRUVrx62Hm0O7MWvveO3Obibj/p3vX+v/X\n" +
                        "xzn77LP3PlITTTTRxP8KwHxgDTBhnPK/ANw42iBTgaMM4rUG1VZP/jVDuX8GZo00yBTgEP/EhgbX\n" +
                        "msv/ain3ceDCeoNMBvrxsetfqj3Nvy3IfQSY4mkqQazXJc0OfCcaUm0exwN7i6S3aooAPBxcDYCX\n" +
                        "G1Zq9TqeyNTxXDXxZRnxujHqIa3nxUw91+aEWwNRT0ZTASaOotgJZKZ2YGNQ03uRoDMQ/AC0ZBI9\n" +
                        "DewBouetWiM7gVcy/rOAr4PabvMEbwfkezJJHkx420fQxJuJfnWGd3NQW1+ZeGVA7M0EX+bwd4yw\n" +
                        "iQL3Z/g7ghrnpaSnAtKtQdCOgA/QA1iVJrZk9IsDTTT0X0pJBxxCf6aQjzKFALwfNUM8oRT4nOB5\n" +
                        "A3Y7/IHCGU25jwfBllcppMBuSrMRsL1G7UNB7lUBv13AvYEzusXV7kaK3kQXjXEPx4DJTu7WgN9V\n" +
                        "kXSJU+8JSR86gTokzfH685qW1M7gummzpGsCjodpkjoc+wFJ3zj22RVJFzmOg2b2o2OfHyS+QdLa\n" +
                        "wHe9pJsC38YhrYerywYz+0PSZw53xkRJ5zsOrwlJutyxHTKzbkndwAxJCwJtGfvM7BZJAg5KKu83\n" +
                        "WgPdMcd2XkXS6Y7jlyDIVMe2t/hhZgsl9QTaFH2S2pP/+xzO9EB70rFNihr5LQgyzbH9lP4xs0WS\n" +
                        "ci/GDyTNNbNTie07r7hA7z2PVpH0u+M4Mwji3dZzhkU1Wyy/mV5J88zsz5Ldu9PRxfTeMVQk/eo4\n" +
                        "TguCeI204ax+h5pJ1187zWyu04QkXeHYjgY1nOHYTlYkfes4Lg6CfOrYLpAzw0iSmXVK2i/pKwWT\n" +
                        "AHCVpEsdV59jK/KVcaQi6bDjaAGGDRlJ7wTBuwK7NNjAHDOL3jXRQrGnbAAmyd+CfyngjuBteZ0X\n" +
                        "HX+9A/BAphkXwJ1BrP0Bvy3MDcwKnM8EwW4P+FDHQRqwKBPnvkDzSMBvKwgfO84viFew0V2BzJ4i\n" +
                        "0d+V0X+S0e1x+APS31OZd8QyU9LyIOYKSd7sI0md1RpRvCyRpJWeEVgiqc1xbUlJ0djz1jWFxtt+\n" +
                        "bq6hiUK/3tHfneG/G9TYUSZGpxWrMsFXJLxNtTaR6Ncl+nCyAJYGtQ1/6QILAjKAt1gsdF3Atnqb\n" +
                        "SPSbgCcz/ukMnvt6WBKJNgSCYXuTsQKDZwAeunOimcCpQFj30Bkt8J+jqqOkEK/MiNePUQ/Vjksf\n" +
                        "rTVINMQgc9bVKABvZPJv9TTRMedS+ZsdKd50NRJRXf2S3LO2EMDZwEDpaqwdbYV15H++lPswcO5I\n" +
                        "g6XNjNm3kST/s0O5vydzkF5rsFbgMcbvq+5qYOF45G6iiSb+g/gLEOV14rPjSCQAAAAASUVORK5C\n" +
                        "YII=").bitmap,
                iconColor = 0xff77de7.toInt(),
                contributorName = "sddpljx"
            ),
            IconDataBean(
                isEnabled = true,
                isEnabledAll = false,
                appName = "小米钱包",
                packageName = "com.miui.tsmclient",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAANlSURBVGiBzZk9iFxVFMd/Z8SkiB/gErKmUBGJWESiWcFCo8VKoo0fiR9BjWAIJN0W\n" +
                        "ooUEFFQUbAIipjABTRox2KhFomgWQYtFK0XBZAURY1QQhFVjdn8Wb0bG3ZmduW/u3eevmWLmnPP/\n" +
                        "3/venfPOCzKjrgLuADYBNwKrgAXgDPA58ElEfJm7bjbUMXW/OutgTqqPNK15CepO9cwQBhYzrV7b\n" +
                        "tH4A1JdqGOhmTr2vaRNHRjTRzQNNmXg5o4kOm1baxC0FTKj+oK6uo6lV08vRmnGDWA/sL5T7v6jb\n" +
                        "C+1Ghz/VS1J11dmRqRoxKawG9qQGRcqP1cuAX1OL1GAmIm5KCRh6R9R1wDOpimoyoW5V697DS1G3\n" +
                        "qe+ofxS+N3pxWj3gKP/+6oR6vAHxvVhQD6rjqSaealh4P35R7x3WxGtNqx2CxwaZeKVphQn0bjTV\n" +
                        "BwsV3K6+USj3FR390TZxMfAjsGbI22hoIiKsVu9Y7tx0/d90zulnKWACeKv9eQKYK5B/Qr0fAHWN\n" +
                        "VX+Tm3l1rFNRfbhADdVpqHZkG1V/k5u7I+LfdiYijgIHC9S5Vb2yBUxmTjwP7IiIdxd/ERF7gQOZ\n" +
                        "6wHc3gKuy5jwPWBjRPS9sSNiCtgBfJ2x7uWon454jZ5Wd9t1FA6LulF9Qv1+RA2HWyS28n0IqiFc\n" +
                        "KhcBF2SoP4f62Yir0c0JhxggqI+rpzLWfbIFfJthRTpMAl/YOdt7mzgMvA5cnbHuLOqejCvTzZ29\n" +
                        "TBSqNY66tlBy1fVdJnYXqnG8e6UOFSrydjv/peq5QjUmu42MWehRtp2/1Ajp446HFkC7lSgy5lef\n" +
                        "B/YWSH0O2Nmv6IuFVq4EW5e1qT7XtMIBzLvM8b7YzC7174YF9+IrdXPSBahuUN9sWHiH39QX1Av7\n" +
                        "6R3YZ6k3ALuAu4ANSasxOjNUj8iHIuJstqxWnepKcVuKtuTOVz1Pno51OU5FxDUpAXWGxO/XiEnl\n" +
                        "SGpAnR3ZApxMjUtkPCJ+SglI3pGImAY+SI1L4NVUE1Dz6VC9CpitEzuAv4C1EfF7amCtFykR8R3w\n" +
                        "aJ3YAdxTx8TIqE9nPG4fWnEDi8xMjWjgZ3s8TTaCukWdqWHimNW7yf8X6j4HT2UW1A9z70KOmdYS\n" +
                        "1OuBm4F1VJPM88A3wFngo4jIObkB4B/bkxHCsVAj/AAAAABJRU5ErkJggg==").bitmap,
                iconColor = 0xff005cff.toInt(),
                contributorName = "sddpljx"
            ),
            // TODO 可继续在这里添加更多 APP
        )
    }
}
