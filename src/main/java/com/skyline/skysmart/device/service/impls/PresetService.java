package com.skyline.skysmart.device.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.interfaces.IPresetBO;
import com.skyline.skysmart.device.data.converter.PresetDataConverter;
import com.skyline.skysmart.device.data.dao.PresetDAO;
import com.skyline.skysmart.device.mapper.PresetMapper;
import com.skyline.skysmart.device.service.interfaces.IPresetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

/**
 * [FEATURE INFO]<br/>
 * preset service
 *
 * @author Skyline
 * @create 2022/6/19 23:24
 * @since 1.0.0
 */
@Service
public class PresetService implements IPresetService {

    private static final String DEFAULT_PRESET_NAME_PREFIX = "_default_preset_";
    private final HashMap<String, IPresetBO> defaultPresetsMap = new HashMap<>();

    private PresetMapper presetMapper;
    private PresetDataConverter presetDataConverter;

    @Autowired
    public void setPresetMapper(PresetMapper presetMapper) {
        this.presetMapper = presetMapper;
    }

    @Autowired
    public void setPresetDataConverter(PresetDataConverter presetDataConverter) {
        this.presetDataConverter = presetDataConverter;
    }

    /**
     * cache default presets when project load
     */
    @Override
    @PostConstruct
    public void cacheDefaultPresets() {
        QueryWrapper<PresetDAO> presetWrapper = new QueryWrapper<>();
        presetWrapper.like("name", DEFAULT_PRESET_NAME_PREFIX);
        List<PresetDAO> presetDAOList = presetMapper.selectList(presetWrapper);

        if (presetDAOList.size() == 0) {
            Asserts.fail(ResultCode.NO_ELEMENT);
        }

        // cache presets to redis
        for (PresetDAO presetDAO : presetDAOList) {
            IPresetBO presetBO = presetDataConverter.constructPresetBO(presetDAO);
            defaultPresetsMap.put(presetBO.getPresetName(), presetBO);
        }
    }

    /**
     * get default preset by preset name
     *
     * @param presetName String
     * @return IPresetBO
     */
    @Override
    public IPresetBO getDefaultPreset(String presetName) {
        return defaultPresetsMap.get(presetName);
    }
}
