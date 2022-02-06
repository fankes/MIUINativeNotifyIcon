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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
                isEnabledAll = false,
                appName = "浏览器",
                packageName = "com.android.browser",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAPVSURBVGiB1ZpPaB1VFMZ/5/EWxcSFLqQJkrYQxeKyi9JdoSI0Fgu6FIm4UWMCKkgV\n" +
                        "CknFRWqLCD4oWKrgqlCEJpSABUHclC5CRUVMN7po81wUhISuuvhczAtvOjl3/r07L8m3eXDOved8\n" +
                        "37szc+eeM0YkSHoSOAUcAcaBsdQvQBdYT/2uAjfMbDMWh9qQNClpTtKK6mOlF2NyJwQcltQZgHwI\n" +
                        "HUmHhyFgQtKipM0GRGxhs5djoikR05K6DQrIoitpOraIM0MUkMWZWCKuN0x0QdJ/BWOuF/FsFYgQ\n" +
                        "cDrKP5KPk8DVHP/pHpfqkLTa8Eps4Xgq55uS7uSMXa0q4vJwNOi+ko00nXtU0nzOnMtlRcwMRUKC\n" +
                        "d3N4nM+ZN1MkYkLDecSuSTpR4k/9OjC/q8w+087MnQH2FyUoiXMB+y3glpltFAUwszlJTwBvZ1z7\n" +
                        "Sbh+sm2SkteOmDv2QkXhQUi66sTfVOp1Jv34fR8YjZU8Mr50bKMknPuQdCjiSkRfkR7H7wN5DkF/\n" +
                        "RQpvvF2AbwP2E9AX8vJwuNSHmf0M/OC4Eu6SRhq4rO5LOhhbjKSpQL6RFjAVOd+vwAUz+ydyXMxs\n" +
                        "BbjnuKbawFHHcQ6YLxHYBuRWB3eBZzO2oy2SAkEWD4CzjVOqh7uObbxNv8qRxgMz60h6BJxvlldl\n" +
                        "/OXYxkIr8jeAmX0BfNQkqxr43bGNI2nDeQo8nR4ladZ7VAyJ+GOQ9IxDZSMkZJ8T4J2iZ25F3JH0\n" +
                        "QQ0h+0JC1hyHd98g6b3IYirvN5LGnDhrLZLyZRZPeUHM7BIwVyVxAcaBqiUfj9t6i6QWW2YwAGbW\n" +
                        "AT6smDwPVfcij1s3tCLHHVs/s9lXwMcVCcTCq45tvQXcdhwvFUUzs4vAp4OyqoHXHNvt0Evjo9AN\n" +
                        "n4WkswPe8KXPLZJOBmKMtMzsIXAtM6dNycKcmX1O+HweG95ldc3MHm6dR246A2Yl7Zqjr6QXgLcc\n" +
                        "103oH6x+cga8CMw2xKsOpoFtGzVZ7vIbN/ckHSjKIOmNAe+TLfwraVnSqUz8Sfn1to5HJlQO+qaE\n" +
                        "kOcjCUljPhV/yfE/Vg7KEloMBPWuzezc5QbEHJP0WcC3mEcmr2T6egkxf0QW8mPAvq1k6pHJK2IX\n" +
                        "Hn+VNG6WlbwQNoX8InaKTF5boVBMSlATKNdWSBHJa/TckHRsB4QEGz3B1puZHcnh+Qrwi5KGzHPF\n" +
                        "f0sc5HHK7SH2yj1LAXcbWAB+k3RFJfodA2ApSulJ5dvTf0r6TslJMtalFac9nRKz9z8YSInZ+59w\n" +
                        "ZATt7Y9qHEG75jOnaEVo7fCHZ/8DGEL3uplW+PsAAAAASUVORK5CYII=").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "lamprose、fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
                isEnabledAll = false,
                appName = "小米视频",
                packageName = "com.miui.video",
                iconBitmap = ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                        "CHwIZIgAAAK0SURBVGiB7Zo9aBRRGEXvZ0RFgq2FBhG0URKwlICIomKTqLAKBpRgo40REksxsRMT\n" +
                        "AmITGyVKBJsECyslWAj+oGsRQRBR1M5IxM4iHIt5SybrzrzJ7nNnlD3lvjP3vcv87OzsSC0KClAC\n" +
                        "5oAysMF9dp10LjjvuMebjM3zzOPudV6fxysDw9UlLldJ07Gxh57AI84b8nhXnbcR+J7ifQQ2J6yr\n" +
                        "FqV4kXINYdyNrQfepwQtADtWuAe7Pd5sbG13Pe5cvEgS5914lyesDLQ5d8bjHnXeKY93K+vhmKUI\n" +
                        "wCHnHPN4085bC7xO8X4BXc694sm85LytpByOWYssAFucd9HjXnPedmA+xfsEtDt3ypNZct6BJCFr\n" +
                        "EYByzJ3wuGect9/jPXXeOuC5x+107rlagyspAnAv5j/2uN3O6/d4k87bSfqV7AOwxrmj1YOSZG5w\n" +
                        "+fU4BTMbJjrU+j3qDTObz5CNmY0AvZJ2+eautd7K5/8FK94jRcTMhitF8MlFxsxsVd6LCEWrSNFo\n" +
                        "FSkaqwPn3ZT0SNImSQclHQ6cn47nNiIrpRq5J4EXgfITCVlkrrpELLsNGAS+BJinJlK4c+Rb0oCZ\n" +
                        "LZrZmKTdksYkLQaacxlNO9nN7KuZDSkqNBU6v+lXLTN7aWZ9knolzfr8rOR2+TWzB2a2T9JZSe8a\n" +
                        "zcv9e8TMJhQdbiON5OReRJLM7If7cXSn3oxCFIlxv94Ni1bkVb0bFq3IiXo3LEwRoluc8Xq3z70I\n" +
                        "0AncVgPnhxT+7jczRE8aByUNSWpvNC+XIsBpRSU6Q2U2tQiwR1GBntDZTSkCdCgqMPC3J2qUn8C2\n" +
                        "hOwB4HOAORIJWQSiv+gq/1x1AD3Ak0DZqUhLj0xbTxqLQqtI0WgVKRqVIm9yXUVjvJWWiszkuJBG\n" +
                        "Wf5bn6WXav4V/nyppkWB+A0UKO3gohxbnAAAAABJRU5ErkJggg==").bitmap,
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
                isEnabledAll = false,
                appName = "阅读",
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            IconDataBean(
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                isEnabledColor = true,
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
                contributorName = "fankes"
            ),
            // TODO 可继续在这里添加更多 APP
        )
    }
}