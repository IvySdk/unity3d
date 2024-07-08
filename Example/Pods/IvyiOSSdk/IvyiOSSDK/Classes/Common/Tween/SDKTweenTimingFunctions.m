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

#import "SDKTweenTimingFunctions.h"

CGFloat SDKTweenTimingFunctionLinear (CGFloat time, CGFloat begin, CGFloat change, CGFloat duration) {
    return change * time / duration + begin;
}

CGFloat SDKTweenTimingFunctionBackOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    CGFloat s = 1.70158;
    return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
}

CGFloat SDKTweenTimingFunctionBackIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    CGFloat s = 1.70158;
    return c*(t/=d)*t*((s+1)*t - s) + b;
}

CGFloat SDKTweenTimingFunctionBackInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    CGFloat s = 1.70158; 
    if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
    return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
}

CGFloat SDKTweenTimingFunctionBounceOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if ((t/=d) < (1/2.75)) {
        return c*(7.5625*t*t) + b;
    } else if (t < (2/2.75)) {
        return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
    } else if (t < (2.5/2.75)) {
        return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
    } else {
        return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
    }
}

CGFloat SDKTweenTimingFunctionBounceIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c - SDKTweenTimingFunctionBounceOut(d-t, 0, c, d) + b;
}

CGFloat SDKTweenTimingFunctionBounceInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if (t < d/2) return SDKTweenTimingFunctionBounceIn(t*2, 0, c, d) * .5 + b;
    else return SDKTweenTimingFunctionBounceOut(t*2-d, 0, c, d) * .5 + c*.5 + b;
}

CGFloat SDKTweenTimingFunctionCircOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c * sqrt(1 - (t=t/d-1)*t) + b;
}

CGFloat SDKTweenTimingFunctionCircIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return -c * (sqrt(1 - (t/=d)*t) - 1) + b;
}

CGFloat SDKTweenTimingFunctionCircInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if ((t/=d/2) < 1) return -c/2 * (sqrt(1 - t*t) - 1) + b;
    return c/2 * (sqrt(1 - (t-=2)*t) + 1) + b;
}

CGFloat SDKTweenTimingFunctionCubicOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c*((t=t/d-1)*t*t + 1) + b;
}

CGFloat SDKTweenTimingFunctionCubicIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c*(t/=d)*t*t + b;
}

CGFloat SDKTweenTimingFunctionCubicInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if ((t/=d/2) < 1) return c/2*t*t*t + b;
    return c/2*((t-=2)*t*t + 2) + b;
}

CGFloat SDKTweenTimingFunctionElasticOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    CGFloat p = d*.3;
    CGFloat s, a;
    if (t==0) return b;  if ((t/=d)==1) return b+c;
    if (!a || a < ABS(c)) { a=c; s=p/4; }
    else s = p/(2*M_PI) * asin (c/a);
    return (a*pow(2,-10*t) * sin( (t*d-s)*(2*M_PI)/p ) + c + b);
}

CGFloat SDKTweenTimingFunctionElasticIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    CGFloat p = d*.3;
    CGFloat s, a;
    if (t==0) return b;  if ((t/=d)==1) return b+c;
    if (!a || a < ABS(c)) { a=c; s=p/4; }
    else s = p/(2*M_PI) * asin (c/a);
    return -(a*pow(2,10*(t-=1)) * sin( (t*d-s)*(2*M_PI)/p )) + b;
}

CGFloat SDKTweenTimingFunctionElasticInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    CGFloat p = d*(.3*1.5);
    CGFloat s, a;
    if (t==0) return b;  if ((t/=d/2)==2) return b+c;
    if (!a || a < ABS(c)) { a=c; s=p/4; }
    else s = p/(2*M_PI) * asin (c/a);
    if (t < 1) return -.5*(a*pow(2,10*(t-=1)) * sin( (t*d-s)*(2*M_PI)/p )) + b;
    return a*pow(2,-10*(t-=1)) * sin( (t*d-s)*(2*M_PI)/p )*.5 + c + b;
}

CGFloat SDKTweenTimingFunctionExpoOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return (t==d) ? b+c : c * (-pow(2, -10 * t/d) + 1) + b;
}

CGFloat SDKTweenTimingFunctionExpoIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return (t==0) ? b : c * pow(2, 10 * (t/d - 1)) + b;
}

CGFloat SDKTweenTimingFunctionExpoInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if (t==0) return b;
    if (t==d) return b+c;
    if ((t/=d/2) < 1) return c/2 * pow(2, 10 * (t - 1)) + b;
    return c/2 * (-pow(2, -10 * --t) + 2) + b;
}

CGFloat SDKTweenTimingFunctionQuadOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return -c *(t/=d)*(t-2) + b;
}

CGFloat SDKTweenTimingFunctionQuadIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c*(t/=d)*t + b;
}

CGFloat SDKTweenTimingFunctionQuadInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if ((t/=d/2) < 1) return c/2*t*t + b;
    return -c/2 * ((--t)*(t-2) - 1) + b;
}

CGFloat SDKTweenTimingFunctionQuartOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return -c * ((t=t/d-1)*t*t*t - 1) + b;
}

CGFloat SDKTweenTimingFunctionQuartIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c*(t/=d)*t*t*t + b;
}

CGFloat SDKTweenTimingFunctionQuartInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
    return -c/2 * ((t-=2)*t*t*t - 2) + b;
}

CGFloat SDKTweenTimingFunctionQuintOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c*(t/=d)*t*t*t*t + b;
}

CGFloat SDKTweenTimingFunctionQuintIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c*((t=t/d-1)*t*t*t*t + 1) + b;
}

CGFloat SDKTweenTimingFunctionQuintInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
    return c/2*((t-=2)*t*t*t*t + 2) + b;
}

CGFloat SDKTweenTimingFunctionSineOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return c * sin(t/d * (M_PI/2)) + b;
}

CGFloat SDKTweenTimingFunctionSineIn (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return -c * cos(t/d * (M_PI/2)) + c + b;
}

CGFloat SDKTweenTimingFunctionSineInOut (CGFloat t, CGFloat b, CGFloat c, CGFloat d) {
    return -c/2 * (cos(M_PI*t/d) - 1) + b;
}

CGFloat SDKTweenTimingFunctionCALinear       (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }
CGFloat SDKTweenTimingFunctionCAEaseIn       (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }
CGFloat SDKTweenTimingFunctionCAEaseOut      (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }
CGFloat SDKTweenTimingFunctionCAEaseInOut    (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }
CGFloat SDKTweenTimingFunctionCADefault      (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }

CGFloat SDKTweenTimingFunctionUIViewLinear       (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }
CGFloat SDKTweenTimingFunctionUIViewEaseIn       (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }
CGFloat SDKTweenTimingFunctionUIViewEaseOut      (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }
CGFloat SDKTweenTimingFunctionUIViewEaseInOut    (CGFloat t, CGFloat b, CGFloat c, CGFloat d) { return 0; }

CGFloat (*SDKTweenTimingFunctionCACustom(CAMediaTimingFunction *timingFunction))(CGFloat, CGFloat, CGFloat, CGFloat) {
    return &SDKTweenTimingFunctionLinear;
}
