package com.jerry.mekextras.common.util;

import mekanism.api.tier.ITier;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;

public interface IExtraUpgradeableTransmitter<DATA extends TransmitterUpgradeData> {

    ITier getTier();

}
