/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.nerd_alert.utils;

import java.util.Timer;
import java.util.TimerTask;

import static net.reflxction.nerd_alert.listeners.ChatListener.setShouldStart;

/**
 * A utility class to manage delays and easily delay code
 */
public class ThreadTask extends TimerTask {

    @Override
    public void run() {
        setShouldStart(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setShouldStart(false);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setShouldStart(true);
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                setShouldStart(false);
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        setShouldStart(true);
                                        new Timer().schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                setShouldStart(false);
                                            }
                                        }, 1500);
                                    }
                                }, 1500);
                            }
                        }, 1500);
                    }
                }, 1500);
            }
        }, 1500);
    }
}
