var _0x5b98ba=_0x2191;(function(_0xc135f2,_0x211808){var _0x1aa2c3=_0x2191,_0x1d7dfc=_0xc135f2();while(!![]){try{var _0x21a494=-parseInt(_0x1aa2c3(0xfa))/0x1*(parseInt(_0x1aa2c3(0x10a))/0x2)+-parseInt(_0x1aa2c3(0x106))/0x3*(parseInt(_0x1aa2c3(0xf6))/0x4)+parseInt(_0x1aa2c3(0x10e))/0x5+-parseInt(_0x1aa2c3(0x10d))/0x6+parseInt(_0x1aa2c3(0xfb))/0x7+-parseInt(_0x1aa2c3(0x10b))/0x8+parseInt(_0x1aa2c3(0x101))/0x9;if(_0x21a494===_0x211808)break;else _0x1d7dfc['push'](_0x1d7dfc['shift']());}catch(_0x1077f0){_0x1d7dfc['push'](_0x1d7dfc['shift']());}}}(_0x3e5e,0xe5862));const format=require(_0x5b98ba(0x102));function isValidDate(_0x177c13){var _0x40c675=_0x5b98ba,_0x50fc8d=/^\d{4}-\d{2}-\d{2}$/;if(!_0x177c13[_0x40c675(0xfe)](_0x50fc8d))return![];var _0x68e486=Date['parse'](_0x177c13);if(isNaN(_0x68e486))return![];return new Date(_0x68e486)[_0x40c675(0xff)]()[_0x40c675(0x10f)](0x0,0xa)===_0x177c13;}function validateTime(_0x3bdf0a){var _0x275e0e=_0x5b98ba,_0x2cc13d=new RegExp(_0x275e0e(0x10c));return _0x2cc13d['test'](_0x3bdf0a)?!![]:![];}function _0x2191(_0x26ecc0,_0x32e2e7){var _0x3e5e35=_0x3e5e();return _0x2191=function(_0x21912e,_0x5c93e0){_0x21912e=_0x21912e-0xf5;var _0x53ab51=_0x3e5e35[_0x21912e];return _0x53ab51;},_0x2191(_0x26ecc0,_0x32e2e7);}function _0x3e5e(){var _0x5d392f=['6991912jntzAL','getEmpId','setEmpId','split','2NBTTKu','12992749ETePzb','Timecard\x20needs\x20to\x20be\x20called\x20with\x20the\x20new\x20keyword','timecard_id','match','toISOString','getStartTime','28268991cVnWxA','date-fns/format','end_time','start_time','setEndTime','3mpApRa','getId','yyyy-MM-dd\x20HH:mm:ss','emp_id','1783108auxRbt','2384064mzTlpG','([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])','1918116iMNGMK','459020bsZFCY','slice','setId','prototype'];_0x3e5e=function(){return _0x5d392f;};return _0x3e5e();}var Timecard=function(_0x1f3d20=format(new Date(),_0x5b98ba(0x108)),_0x46f392=format(new Date(),'yyyy-MM-dd\x20HH:mm:ss'),_0x588a1d=0x0,_0xcf0560=null){var _0x3a22eb=_0x5b98ba;if(!(this instanceof Timecard))throw new Error(_0x3a22eb(0xfc));this[_0x3a22eb(0x109)]=_0x588a1d,this[_0x3a22eb(0xfd)]=_0xcf0560;var _0x3820c6=_0x1f3d20[_0x3a22eb(0xf9)]('\x20');isValidDate(_0x3820c6[0x0])&&validateTime(_0x3820c6[0x1])?this[_0x3a22eb(0x104)]=_0x1f3d20:this[_0x3a22eb(0x104)]=format(new Date(),_0x3a22eb(0x108));var _0x2cf430=_0x46f392[_0x3a22eb(0xf9)]('\x20');isValidDate(_0x2cf430[0x0])&&validateTime(_0x2cf430[0x1])?this[_0x3a22eb(0x103)]=_0x46f392:this[_0x3a22eb(0x103)]=format(new Date(),_0x3a22eb(0x108));};Timecard[_0x5b98ba(0xf5)][_0x5b98ba(0x107)]=function(){var _0x115f52=_0x5b98ba;return this[_0x115f52(0xfd)];},Timecard[_0x5b98ba(0xf5)][_0x5b98ba(0x100)]=function(){var _0x15ced9=_0x5b98ba;return this[_0x15ced9(0x104)];},Timecard[_0x5b98ba(0xf5)]['getEndTime']=function(){var _0xd1a4bf=_0x5b98ba;return this[_0xd1a4bf(0x103)];},Timecard[_0x5b98ba(0xf5)][_0x5b98ba(0xf7)]=function(){var _0x5e094e=_0x5b98ba;return this[_0x5e094e(0x109)];},Timecard['prototype'][_0x5b98ba(0x110)]=function(_0x5ae222){var _0x811b94=_0x5b98ba;this[_0x811b94(0xfd)]=_0x5ae222;},Timecard[_0x5b98ba(0xf5)]['setStartTime']=function(_0x3361a1){var _0x360163=_0x5b98ba,_0x2657a8=_0x3361a1[_0x360163(0xf9)]('\x20');isValidDate(_0x2657a8[0x0])&&validateTime(_0x2657a8[0x1])?this['start_time']=_0x3361a1:this['start_time']=format(new Date(),'yyyy-MM-dd\x20HH:mm:ss');},Timecard[_0x5b98ba(0xf5)][_0x5b98ba(0x105)]=function(_0xa0cd5f){var _0x9448ea=_0x5b98ba,_0x3011ae=_0xa0cd5f['split']('\x20');isValidDate(_0x3011ae[0x0])&&validateTime(_0x3011ae[0x1])?this[_0x9448ea(0x103)]=_0xa0cd5f:this[_0x9448ea(0x103)]=format(new Date(),_0x9448ea(0x108));},Timecard['prototype'][_0x5b98ba(0xf8)]=function(_0x40ca21){var _0xb1c524=_0x5b98ba;this[_0xb1c524(0x109)]=_0x40ca21;},module['exports']=Timecard;