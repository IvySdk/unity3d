/*
 
 These timing functions are adapted from Robert Penner's excellent AS2 easing equations.
 For more information, check out http://robertpenner.com/easing/
 
 --
 
 TERMS OF USE - EASING EQUATIONS
 
 Open source under the BSD License. 
 
 Copyright © 2001 Robert Penner
 All rights reserved.
 
 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 
 Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 Neither the name of the author nor the names of contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 
*/

#import <Foundation/Foundation.h>
#import <QuartzCore/QuartzCore.h>

CGFloat SDKTweenTimingFunctionLinear (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionBackOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionBackIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionBackInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionBounceOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionBounceIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionBounceInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionCircOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCircIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCircInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionCubicOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCubicIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCubicInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionElasticOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionElasticIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionElasticInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionExpoOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionExpoIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionExpoInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionQuadOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionQuadIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionQuadInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionQuartOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionQuartIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionQuartInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionQuintOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionQuintIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionQuintInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionSineOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionSineIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionSineInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionCALinear (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCAEaseIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCAEaseOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCAEaseInOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionCADefault (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat SDKTweenTimingFunctionUIViewLinear (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionUIViewEaseIn (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionUIViewEaseOut (CGFloat, CGFloat, CGFloat, CGFloat);
CGFloat SDKTweenTimingFunctionUIViewEaseInOut (CGFloat, CGFloat, CGFloat, CGFloat);

CGFloat (*SDKTweenTimingFunctionCACustom(CAMediaTimingFunction *timingFunction))(CGFloat, CGFloat, CGFloat, CGFloat);


