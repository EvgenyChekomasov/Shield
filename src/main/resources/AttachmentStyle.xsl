<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>
    <xsl:attribute-set name="tableBorder">
        <xsl:attribute name="border">
            solid 0.25mm black
        </xsl:attribute>
    </xsl:attribute-set>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA3"
                                    page-height="29.7cm" page-width="42.0cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simpleA3">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="10pt">
                        <fo:table table-layout="fixed" width="100%" border-collapse="separate">
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-column column-width="1cm"/>
                            <fo:table-header>
                                <fo:table-row height="15mm">
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Мощность, кВт</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Коэф-т мощности</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Коэф-т спроса</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Расчетный ток, А</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>Потребитель</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-header>
                            <fo:table-body>
                                <xsl:for-each select="Attachment/attachment">
                                <fo:table-row>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="power"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="cosFi"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="ratio_demand"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="current"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="breaker_mark"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="poles"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="breaker_current"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="cable1_name"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="cable1_mark"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="cable1_profile"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="contactor"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="contactor_current"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="cable2_name"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="cable2_mark"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="cable2_profile"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="control_cable_name"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="control_cable_mark"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="control_cable_profile"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="control_post"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                        <fo:block>
                                            <xsl:value-of select="consumer"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    <!--xsl:template match="attachment">
        <fo:table-row>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="power"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="cosFi"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="ratio_demand"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="current"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="breaker_mark"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <vblock>
                    <xsl:value-of select="poles"/>
                </vblock>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="breaker_current"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="cable1_name"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="cable1_mark"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="cable1_profile"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="contactor"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="contactor_current"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="cable2_name"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="cable2_mark"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="cable2_profile"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="control_cable_name"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="control_cable_mark"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="control_cable_profile"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="control_post"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                <fo:block>
                    <xsl:value-of select="consumer"/>
                </fo:block>
            </fo:table-cell>
        </fo:table-row>
    </xsl:template-->
</xsl:stylesheet>